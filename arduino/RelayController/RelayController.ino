
const int UP = 0;
const int DOWN = 1;
const int PRESS = 2;

const int RC_SUCCESS=0;
const int RC_PIN_OUT_OF_RANGE=1;
const int RC_UNKNOWN_ACTION=2;

void setup(){
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  pinMode(13, OUTPUT);
  Serial.begin(9600);
}

void loop(){
  if (Serial.available())  {
    int pin = Serial.parseInt();
    int action = Serial.parseInt();
    if (Serial.read() == '\n') {
      int result = doAction(pin, action);
      Serial.print(result);
    }
    Serial.flush();//clear anything leftover coming in
  }
}

int doAction(int pin, int action){
  if(pin>=8 && pin<=13) {
    switch(action) {
      case UP: return switchUp(pin);
      case DOWN: return switchDown(pin);
      case PRESS: return switchPress(pin);
      default: return RC_UNKNOWN_ACTION;
    }
  }
  return RC_PIN_OUT_OF_RANGE;
}

int switchUp(int pin) {
  digitalWrite(pin, LOW);
  return RC_SUCCESS;
  
}

int switchDown(int pin) {
  digitalWrite(pin, HIGH);
  return RC_SUCCESS;
}

int switchPress(int pin) {
  switchDown(pin);
  delay(500);
  switchUp(pin);
  return RC_SUCCESS;
}
