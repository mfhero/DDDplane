#CURRENT_DIR := $(shell pwd)  
PROJECT := DDDplane
BUILD_DIR := build

EXEC_NAME := Main
SRC := $(shell find src/ -name '*.java')
CLASSES := $(BUILD_DIR)/$(EXEC_NAME).class
TARGET := $(PROJECT).jar 
OUT := out  

JFLAGS := -encoding UTF-8
JAVAC := javac

.PHONY: all test jar clean

all: $(CLASSES)

$(CLASSES): $(SRC) 
	mkdir -p $(BUILD_DIR)
	$(JAVAC) -cp src/ -d $(BUILD_DIR) $(SRC) 

run: $(CLASSES)
	java -cp $(BUILD_DIR) $(EXEC_NAME)

jar: $(CLASSES)
	jar -cvfe $(TARGET) $(EXEC_NAME) -C $(BUILD_DIR) .

clean:
	rm -rf $(BUILD_DIR)
	rm -f $(TARGET)

#javac: preinit  
#	javac -cp $(CURRENT_DIR)/$(OUT)/$(PROJECT) -d $(CURRENT_DIR)/$(OUT)/$(PROJECT)  @$(CURRENT_DIR)/$(SOURCE)  
#	rm -f $(CURRENT_DIR)/$(SOURCE)  

#preinit:  
#	@find $(CURRENT_DIR)/ -name "*.java" > $(CURRENT_DIR)/$(SOURCE)  
#	mkdir -p $(CURRENT_DIR)/$(OUT)/$(PROJECT)   
#
#jar: javac  
#	jar -cvf $(TARGET) -C $(CURRENT)/$(OUT)/$(PROJECT) .  
#
#clean:  
#	rm -rf $(OUT)  
