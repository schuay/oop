JAVAC = javac -g -Werror -implicit:none -Xlint:unchecked
JAVA = java
SOURCES = Animal.java Coupling.java Dolphin.java DwarfTortoise.java \
		  DwarfTortoiseTerrarium.java Elephant.java GiantTortoise.java \
		  GiantTortoiseTerrarium.java Giraffe.java LargeGame.java Loadable.java \
		  Mamba.java MambaTerrarium.java Python.java PythonTerrarium.java \
		  Reptile.java SeaAnimal.java Seal.java Swordfish.java Tank.java \
		  Terrarium.java Test.java Trailer.java Transporter.java \
		  TransportObject.java WaterLandTank.java WaterTank.java
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
