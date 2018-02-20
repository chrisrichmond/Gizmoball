package Model;

import Model.gizmos.Absorber;
import Model.gizmos.CircularBumper;
import Model.gizmos.SquareBumper;
import Model.gizmos.TriangularBumper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GizmoFileHandler {

    private ModelAPI model;
    private List<String> tokenList;

    public GizmoFileHandler(ModelAPI model){
        this.model = model;
    }

    public void writeToFile(){

    }

    public void readFromFile(String filename){
        String currentLine = null;
        String opcode = null;
        List<String> operands = null;

        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                opcode = getOpcode(currentLine);
                operands = getOperands(currentLine);
                loadObjectIntoModel(opcode, operands);
            }

            br.close();

        }catch(IOException iox){
            System.out.print("Error trying to read file '"+filename+"': ");
            iox.printStackTrace();
        }

        // for each line in file
            // read line
            // get opcode
            // get operands
            // if opcode involves creating new gizmo
                // add new gizmo to model
            // else
                // do stuff for connecting etc (??)
            // end if
        // end loop


    }

    private void loadObjectIntoModel(String opcode, List<String> operands){
        opcode = opcode.toLowerCase();
        for(String currentOperand: operands){
            currentOperand = currentOperand.toLowerCase();
        }

        if(opcode.equals("blank")) {
            // object is blank, add nothing
        }else if(operands.get(0).equals("blank")) {
            // object is blank, add nothing
        }else if(opcode.equals("ball")) {
            // object is a ball

            //model.addBall(...)??
        }else{
            // object is a gizmo
            int x = Integer.parseInt(operands.get(1));
            int y = Integer.parseInt(operands.get(2));
            if (opcode.equals("square")) {
                model.addGizmo(new SquareBumper(operands.get(0), x, y));
            } else if (opcode.equals("circle")) {
                model.addGizmo(new CircularBumper(operands.get(0), x, y));
            } else if (opcode.equals("triangle")) {
                model.addGizmo(new TriangularBumper(operands.get(0), x, y));
            } else if (opcode.equals("absorber")) {
                model.addGizmo(new Absorber(operands.get(0), x, y));
            }
        }
    }


    // Parser methods
    private List<String> getTokens(String line){
        tokenList = new ArrayList<String>();

        StringTokenizer tokens = new StringTokenizer(line);
        while(tokens.hasMoreTokens()){
            tokenList.add(tokens.nextToken());
        }

        return tokenList;
    }

    private String getOpcode(String line){
        try {
            return getTokens(line).get(0);
        }catch(NullPointerException npx){
            System.out.println("NullPointerException trying to get opcode from line '"+line+"'");
            System.out.println("Giving 'blank' opcode instead");
            return "blank";
        }
    }

    private List<String> getOperands(String line){
        List<String> operands = new ArrayList<String>();

        try{
            operands.remove(0);
            try{
                return operands;
            }catch(NullPointerException npx){
                System.out.println("Only one token detected in line '"+line+"' ?");
                System.out.println("Giving blank operand instead");
                operands.add("blank");
                return operands;
            }
        }catch(NullPointerException npx){
            System.out.println("NullPointerException trying to get operand(s) from line '"+line+"'");
            System.out.println("Giving 'blank' operand instead");
            operands.add("blank");
            return operands;
        }
    }





}
