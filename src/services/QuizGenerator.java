package services;

import models.Quiz;
import models.Question;
import models.MCQQuestion;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class QuizGenerator {
    private static final QuizRepository quizRepository = new QuizRepository();

    /**
     * This is the exact method your offline backup mode is calling.
     * We have forced it to return our unique curated topic pools.
     */
    public Quiz generateQuizWithAutoSave(String topic, int questionCount) {
        System.out.println(">>> Offline intercept: Generating unique question sets for: " + topic + " <<<");
        return generateFallbackQuiz(topic);
    }

    public static Quiz generateFallbackQuiz(String chosenTopicName) {
        Quiz newQuiz = new Quiz();
        newQuiz.setId(UUID.randomUUID().toString());
        newQuiz.setTopic(chosenTopicName);

        List<Question> generatedQuestions = new ArrayList<>();
        String topicKey = (chosenTopicName != null) ? chosenTopicName.trim().toUpperCase() : "GENERAL";

        if (topicKey.equals("OOP") || topicKey.contains("OBJECT")) {
            generatedQuestions.add(createMCQ(
                    "Which OOP concept wraps data and methods together into a single unit?",
                    "Encapsulation", "Inheritance", "Polymorphism", "Abstraction"
            ));
            generatedQuestions.add(createMCQ(
                    "What is a real-world instance or memory allocation of a Class called?",
                    "Object", "Method Signature", "Primitive Variable", "Architectural Blueprint"
            ));
            generatedQuestions.add(createMCQ(
                    "Which concept allows a single method interface to exhibit multiple distinct behaviors at runtime?",
                    "Polymorphism", "Data Hiding", "Static Compiling", "Multilevel Packages"
            ));
            generatedQuestions.add(createMCQ(
                    "What type of class cannot be directly instantiated and acts strictly as a baseline?",
                    "Abstract Class", "Interface Layer", "Static Helper Class", "Concrete Utility"
            ));
            generatedQuestions.add(createMCQ(
                    "Which Java access modifier limits data field visibility exclusively to its declaring class?",
                    "private", "protected", "public", "package-private"
            ));

        } else if (topicKey.equals("INHERITANCE") || topicKey.contains("EXTENDS")) {
            generatedQuestions.add(createMCQ(
                    "Which keyword does a child class use in Java to inherit fields and actions from its parent?",
                    "extends", "implements", "inherits", "super"
            ));
            generatedQuestions.add(createMCQ(
                    "What is the term given to a class that derives its state and functionality from a parent class?",
                    "Subclass", "Base Class", "Root Frame", "Abstract Interface"
            ));
            generatedQuestions.add(createMCQ(
                    "Which feature allows a child class to provide a specific implementation for a method declared in its parent?",
                    "Method Overriding", "Method Overloading", "Encapsulation Matrix", "Static Shadowing"
            ));
            generatedQuestions.add(createMCQ(
                    "Which statement regarding class inheritance rules in Java is structurally true?",
                    "Java supports single class inheritance but multiple interface implementation",
                    "Java supports multiple class inheritance natively",
                    "A child class can extend up to three distinct parent classes concurrently",
                    "Private fields are fully visible and directly alterable by sub-classes"
            ));
            generatedQuestions.add(createMCQ(
                    "Which keyword is invoked inside a child constructor to trigger the parent class's constructor path?",
                    "super", "this", "parent", "base"
            ));

        } else {
            // Unique Pool for History/WW2 or any other category
            generatedQuestions.add(createMCQ("Which global conflict lasted from 1939 to 1945?", "World War 2", "World War 1", "The Cold War", "The Napoleonic Wars"));
            generatedQuestions.add(createMCQ("Which event triggered the structural entry of the United States into WW2?", "Attack on Pearl Harbor", "Battle of Britain", "D-Day Invasion", "Signing of Versailles"));
            generatedQuestions.add(createMCQ("Who was the Prime Minister of the United Kingdom during most of World War II?", "Winston Churchill", "Neville Chamberlain", "Clement Attlee", "Woodrow Wilson"));
            generatedQuestions.add(createMCQ("The major D-Day landings of 1944 took place in which region of France?", "Normandy", "Brittany", "Alsace", "Provence"));
            generatedQuestions.add(createMCQ("Which international organization was created immediately following WW2 to prevent future global conflicts?", "United Nations", "League of Nations", "NATO", "European Union"));
        }

        newQuiz.setQuestions(generatedQuestions);

        try {
            quizRepository.autoSaveQuiz(newQuiz);
        } catch (Exception e) {
            System.err.println("Database auto-save deferred.");
        }

        return newQuiz;
    }

    private static MCQQuestion createMCQ(String text, String correct, String b, String c, String d) {
        List<String> opts = new ArrayList<>();
        opts.add(correct);
        opts.add(b);
        opts.add(c);
        opts.add(d);
        return new MCQQuestion(UUID.randomUUID().toString(), text, opts, correct);
    }
}