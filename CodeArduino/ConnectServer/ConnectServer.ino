#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include "DHT.h"

DHT dht(D4, DHT22);
#define soilPin A0
#define ledPin D3

const char* ssid = "Free";
const char* password = "20102001";
const char *positions[25] = {"16,17", "16,25", "18,87", "20,130", "21,88", "22,111", "23,97", "24,109", "25,54", "25,124", "26,01,", "26,97", "32,10", "32,32", "33,39", "33,44", "33,99", "34,53", "34,97", "35,30", "35,109", "36,50", "36,121", "37,05", "38,145"};


String serverName = "http://leducnhat.xyz/api/v1/user/data";

unsigned long lastTime = 0;
unsigned long timerDelay = 5000;


void setup() {
  pinMode(ledPin, OUTPUT);
  pinMode(soilPin, INPUT);
  dht.begin();
  Serial.begin(115200);
  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
  Serial.println("Timer set to 5 seconds (timerDelay variable), it will take 5 seconds before publishing the first reading.");
  digitalWrite(ledPin,0);
}

void loop() {
  int pos_i = random(25);
  int h = dht.readHumidity();
  int t = dht.readTemperature();
  int s = analogRead(soilPin);
  int sVal = map(s, 0, 1024, 100, 0);
  String temp = String(t);
  String hum = String(h);
  String soil = String(sVal);
  sendDataHTTP(temp, hum, soil, String(positions[pos_i]));
}

void sendDataHTTP(String t, String h, String s, String p) {
  if ((millis() - lastTime) > timerDelay) {
    //Check WiFi connection status
    if (WiFi.status() == WL_CONNECTED) {
      WiFiClient client;
      HTTPClient http;

      // Your Domain name with URL path or IP address with path
      http.begin(client, serverName.c_str());
      http.addHeader("Content-Type", "application/json");
      String bodystr = "{\"temperature\": \"";
      bodystr.concat(t);
      bodystr.concat("\",\"temperatureMeasure\":\"");
      bodystr.concat("CELSIUS");
      bodystr.concat("\",\"humidity\":\"");
      bodystr.concat(h);
      bodystr.concat("\",\"humidityMeasure\":\"");
      bodystr.concat("PERCENT");
      bodystr.concat("\",\"position\":\"");
      bodystr.concat(p);
      bodystr.concat("\",\"soilMoisture\":\"");
      bodystr.concat(s);
      bodystr.concat("\",\"soilMoistureMeasure\":\"");
      bodystr.concat("PERCENT");

      bodystr.concat("\"}");
      // Send HTTP GET request
      int httpResponseCode = http.POST(bodystr.c_str());

      if (httpResponseCode > 0) {
        Serial.print("HTTP Response code: ");
        Serial.println(httpResponseCode);
        String payload = http.getString();
        Serial.println(payload);
        blinkLed();
      }
      else {
        Serial.print("Error code: ");
        Serial.println(httpResponseCode);
      }
      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }
    lastTime = millis();
  }
}

void blinkLed() {
  digitalWrite(ledPin,1);
  delay(100);
  digitalWrite(ledPin,0);
  delay(100);
}

//#include "DHT.h"
//DHT dht(D4, DHT22);
//#define soilPin A0
//#define ledPin D3
//void setup() {
//  pinMode(ledPin, OUTPUT);
//  pinMode(soilPin, INPUT);
//  Serial.begin(115200);
//  dht.begin();
//}
//
//void loop() {
//  //  float h = dht.readHumidity();
//  //  float t = dht.readTemperature();
//  //  int s = analogRead(soilPin);
//
//  int h = dht.readHumidity();
//  int t = dht.readTemperature();
//  int s = analogRead(soilPin);
//  int sVal = map(s, 0, 1024, 0, 100);
//
//  String temp = String(t);
//  String hum = String(h);
//  String soil = String(sVal);
//
//  //  Serial.println(t);
//  //  Serial.println(h);
//  //  Serial.println(s);
//
//  Serial.println(temp);
//  Serial.println(hum);
//  Serial.println(soil);
//  Serial.println("---------------");
//  delay(200);
//}
