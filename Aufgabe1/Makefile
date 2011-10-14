JAVAC = javac -g
JAVA = java
JAR = jar
SOURCES = LVA.java LVAManager.java Student.java Test.java
CLASSES = $(SOURCES:.java=.class)
MODNAME = Test

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

test: $(CLASSES)
	$(JAVA) $(MODNAME)

package: $(SOURCES)
	$(JAR) -Mcvf $(MODNAME).jar $(SOURCES)

clean:
	rm -f $(CLASSES)
	rm -f $(MODNAME).jar
