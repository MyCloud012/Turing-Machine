/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package turingmachineexample;

import java.util.ArrayList;

/**
 *
 * @author Mycloud012
 */
public class TuringMachineExample {

  // This turing machiens checks whether or not  L = 0^N 1^N where alphabet {0,1} 
    // It is based on this video tutorial https://www.youtube.com/watch?v=VNgctOzDO-I
    // It contained one bug at certain transition, and I've fixed it.
    public static State getTransition(State state, char readLetter) {
        State x = new State();
        x.setName(state.getName());

        if (state.getName().equals("A")) {
            if (readLetter == '0') {
                x.setToWrite('X');
                x.setDirection(State.Directions.RIGHT);
                x.setNextState("B");
                return x;

            } else if (readLetter == 'X') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.RIGHT);
                x.setNextState(state.getName());
            } else if (readLetter == 'Y') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.RIGHT);
                x.setNextState("D");
                return x;
            } else if (readLetter == '$') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.LEFT);
                x.setNextState("Accept");
            }
        } else if (state.getName().equals("B")) {
            if (readLetter == '0' || readLetter == 'Y') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.RIGHT);
                x.setNextState(state.getName());
                return x;
            } else if (readLetter == '1') {
                x.setToWrite('Y');
                x.setDirection(State.Directions.LEFT);
                x.setNextState("C");
                return x;
            }
        } else if (state.getName().equals("C")) {
            if (readLetter == '0' || readLetter == 'Y') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.LEFT);
                x.setNextState(state.getName());
                return x;
            } else if (readLetter == 'X') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.RIGHT);
                x.setNextState("A");
                return x;
            }
        } else if (state.getName().equals("D")) {
            if (readLetter == 'Y') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.RIGHT);
                x.setNextState(state.getName());
                return x;
            } else if (readLetter == '$') {
                x.setToWrite(readLetter);
                x.setDirection(State.Directions.LEFT);
                x.setNextState("Accept");

                System.out.println("Accepeted");
                System.exit(0);
            }
        }


        return x;
    }
    
    public static void runMachine()
    {
        ArrayList<Character> tape = new ArrayList<Character>();
        tape.add('0');
        tape.add('0');
        tape.add('0');
        tape.add('0');
        tape.add('0');


        tape.add('1');
        tape.add('1');
        tape.add('1');
        tape.add('1');
        tape.add('1');

        tape.add('$');


        State temp = new State();
        temp.setName("A");

        for (int i = 0; i < tape.size();) {
            temp = getTransition(temp, tape.get(i));

            if (temp.getName().equals("")) {
                System.out.println("Rejected");
                System.exit(0);
            }

            System.out.println("State: " + temp.getName() + " Read Letter: " + tape.get(i));

            System.out.println("Writing: " + tape.get(i) + " , " + temp.getToWrite());
            tape.set(i, temp.getToWrite());

            try {
                if (temp.getDirection() == State.Directions.RIGHT) {
                    i += 1;
                    System.out.println("Moving Right " + "i = " + i + " letter: " + tape.get(i));
                    System.out.println();
                } else if (temp.getDirection() == State.Directions.LEFT) {
                    i = i - 1;
                    System.out.println("Moving Left");
                } else {
                    System.out.println("Staying in place");
                }

            } catch (Exception e) {
                System.out.println("Throwing an exception");
            }

            State next = new State();
            next.setName(temp.getNextState());

            temp = next;

        }
    }
    public static void main(String[] args) {
        runMachine();
        
        
    }
}
