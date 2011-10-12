JAVAC = javac -g
JAR = jar
SOURCES = LVA.java LVAManager.java Student.java
CLASSES = $(SOURCES:.java=.class)
MODNAME = LVAManager

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

package: $(SOURCES)
	$(JAR) -Mcvf $(MODNAME).jar $(SOURCES)

clean:
	rm -f $(CLASSES)
	rm -f $(MODNAME).jar
