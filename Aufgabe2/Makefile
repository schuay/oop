JAVAC = javac -g
JAVA = java
JAR = jar
SOURCES = Course.java CourseManager.java Student.java Test.java \
		  Person.java Employable.java \
		  Gradeable.java Grade.java Tutor.java Enrollable.java \
		  BaseEnrollable.java Util.java Certificate.java Exam.java \
		  ExerciseInterview.java Group.java Requirements.java \
		  Professor.java
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
