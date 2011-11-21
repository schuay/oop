JAVAC = javac -g -Werror -impicit:none
JAVA = java
JAR = jar
SOURCES = InorderTree.java PostorderTree.java ReplaceableTree.java \
		  StringTree.java IntTree.java PreorderTree.java \
		  SortedTree.java Test.java Replaceable.java Tree.java Node.java \
		  StringNode.java IntNode.java StringStack.java IntStack.java
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
