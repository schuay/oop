JAVAC = javac -g -Werror -implicit:none -Xlint:unchecked
JAVA = java
SOURCES = ColdStorage.java Cook.java Hunter.java Logistician.java \
		  Lumberjack.java Quarry.java Storage.java Table.java \
		  Test.java VerschenknixAG.java VillageSquare.java \
		  Worker.java Util.java
CLASSES = $(SOURCES:.java=.class)
MODNAME = Test

CURDIR = $(shell pwd)

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $<

test: $(CLASSES)
	$(JAVA) $(MODNAME)

clean:
	rm -f $(CLASSES) $(CLASSES:.class=\$$*.class)

hand-in:
	rsync -rlIv \
		--exclude Makefile \
		--exclude README \
		--exclude-from "$(CURDIR)/../.gitignore" \
		--chmod=o-rwxs,Dug+x,u+rw,g+r \
		--delete \
		-e "ssh -F $(CURDIR)/../.ssh-cfg" \
		$(CURDIR)/ "oop-handin:Gruppe/$(shell basename $(CURDIR))"
