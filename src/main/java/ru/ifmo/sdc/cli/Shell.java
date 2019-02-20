package ru.ifmo.sdc.cli;

import ru.ifmo.sdc.cli.commands.Command;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) throws IOException {
        new Shell().run();
    }

    private void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Environment environment = new Environment();
        Parser parser = new Parser();
        while (true) {
            System.out.print("$ ");
            String line = scanner.nextLine();
            if (line.trim().equals("exit")) {
                break;
            }
            List<Command> commands = parser.parse(line, environment);
            String prevResult = "";
            for (Command command : commands) {
                prevResult = command.execute(prevResult);
            }
            if (!prevResult.isEmpty()) {
                System.out.println(prevResult);
            }
        }
    }
}
