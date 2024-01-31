import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;

import java.util.Properties;
import java.util.Scanner;

public class NLPChatBot {
    public static void main(String[] args) {
        // Set up pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

        // Set up Stanford CoreNLP pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Hello! I'm an NLP chatbot. Type something or type 'exit' to end the conversation.");

        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            // Annotate an input string
            Annotation annotation = new Annotation(input);
            pipeline.annotate(annotation);

            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);

                System.out.println("The sentiment of your input is: " + (sentiment > 2 ? "Positive" : "Negative"));
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }
}