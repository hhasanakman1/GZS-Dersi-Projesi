#include "DHT.h"
#define DHTPIN 34    
#define DHTTYPE DHT11 //sıcaklık sensörü ismi değiştirildi  
DHT dht(DHTPIN, DHTTYPE);
#define yesilLed_pin 31 //yeşil toprak nemin doygunlugu
#define kirmiziLed_pin 32 // kırmızı led nemin az oldugunu 
#define motorLed_pin 33 // topragın sulanması gerektiğini
#define toprak_sensoru A14
#define calisma_zamani 600  
#include <LiquidCrystal.h>
LiquidCrystal lcd(8, 9, 4, 5, 6, 7);           
int nem = 0;

void setup() {
    pinMode(yesilLed_pin,OUTPUT);
    pinMode(kirmiziLed_pin,OUTPUT);
    pinMode(motorLed_pin,OUTPUT);
    Serial.begin(9600); 
    lcd.begin(16, 2);              
    lcd.setCursor(0, 0); 
    dht.begin();
}

void loop() {
    int h = dht.readHumidity(); //sensörden okunan nem değeri
    int t = dht.readTemperature(); // sensörden okunan sıcaklık degeri
    if (isnan(t) || isnan(h)) {
    Serial.println("Sıcaklık ve nem değeri algılanmadı");
  } else {
    lcd.setCursor(0, 0);
    lcd.print("Nem:");
    lcd.print("%"); 
    lcd.print(h);
    lcd.setCursor(9, 0);
    lcd.print("S:"); 
    lcd.print(t);
    lcd.print("C");
    
    
   
  
  }
    nem = analogRead(toprak_sensoru);
    lcd.setCursor(0, 1);
    lcd.print("ToprakNem:");
    lcd.println(nem);
    delay(500);
    if(nem > calisma_zamani){
    digitalWrite(motorLed_pin,HIGH);
    digitalWrite(kirmiziLed_pin,HIGH);
    digitalWrite(yesilLed_pin,LOW);
    delay(300);
    digitalWrite(motorLed_pin,HIGH);
    delay(300);
    digitalWrite(motorLed_pin,LOW);
    
  }else{
    digitalWrite(yesilLed_pin,HIGH);
    digitalWrite(motorLed_pin,LOW);
    digitalWrite(kirmiziLed_pin,LOW);
  }
    Serial.print(h);
    Serial.print(" ");
    Serial.print(t);
    Serial.print(" ");
    Serial.print(nem);
    Serial.println(" ");
}

