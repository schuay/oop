JAVAC = javac -g
JAVA = java
JAR = jar
SOURCES = Course.java CourseManager.java Student.java Test.java \
		  EnrollmentException.java Person.java Employable.java \
		  Gradeable.java Grade.java Tutor.java Enrollable.java \
		  BasicEvent.java Util.java Assignment.java \
		  Certificate.java CourseType.java Exam.java \
		  ExerciseInterview.java Group.java SignUp.java
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
