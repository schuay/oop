JAVAC = javac -g -Werror -implicit:none -Xlint:unchecked
JAVA = java
SOURCES = InorderTree.java Iter.java Person.java PostorderTree.java \
		  PreorderTree.java Professor.java ReplaceableTree.java \
		  SortedTree.java Student.java Test.java TreeIter.java Tree.java
CLASSES = $(SOURCES:.java=.class)
MODNAME = Test

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

test: $(CLASSES)
	$(JAVA) $(MODNAME)

clean:
	rm -f $(CLASSES)
	rm -f $(MODNAME).jar
