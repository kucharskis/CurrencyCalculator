import picocli.CommandLine;

public class App {
    public static void main(String[] args) throws Exception {
        CommandLineApp commandLine = new CommandLineApp();
        new CommandLine(commandLine).parse(args);
        commandLine.run();
    }
}