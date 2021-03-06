package Model;

import Model.gizmos.*;

import java.io.*;
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

    public void loadFromFile(String filename){
        String currentLine = null;
        String opcode = null;
        List<String> operands = null;

        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while((currentLine = br.readLine()) != null) {
                if(currentLine.length() > 0) {
                    opcode = getOpcode(currentLine);
                    operands = getOperands(currentLine);
                    loadObjectIntoModel(opcode, operands);
                }
            }

            br.close();

        }catch(IOException iox){
            System.out.print("Error trying to read file '"+filename+"': ");
            iox.printStackTrace();
        }
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
            Ball newBall = new BallImpl(operands.get(0), Float.parseFloat(operands.get(1)), Float.parseFloat(operands.get(2)), Float.parseFloat(operands.get(3)), Float.parseFloat(operands.get(4)));
            model.replaceBall(newBall);
        }else if(opcode.equals("rotate")) {
            // operation is to rotate an object
            for (Gizmo currentGizmo : model.getGizmos()) {
                if (currentGizmo.getID().equals(operands.get(0))) {
                    model.rotateGizmo(currentGizmo);
                }
            }
        }else if(opcode.equals("gravity")) {
            // operation is set gravity
            model.setGravity(Double.parseDouble(operands.get(0)));
        }else if(opcode.equals("friction")){
            // operation is set friciton

            model.setMu(Double.parseDouble(operands.get(0)));
            model.setMu2(Double.parseDouble(operands.get(1)));
        }else if(opcode.equals("keyconnect")) {
            // operation is a key connect
            // not implemented yet so do nothing
        }else if(opcode.equals("connect")) {
            // operation is a gizmo connect
            // not implemented yet so do nothing

            Gizmo firstGizmo = model.getGizmoByID(operands.get(0));
            Gizmo secondGizmo = model.getGizmoByID(operands.get(1));

            if((firstGizmo == null)||(secondGizmo == null)){
                System.out.println("invalid gizmo connection load operation");
            }else {
                model.addGizmoConnection(firstGizmo, secondGizmo);
            }

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
                int x2 = Integer.parseInt(operands.get(3));
                int y2 = Integer.parseInt(operands.get(4));
                model.addGizmo(new Absorber(operands.get(0), x, y, x2, y2));
            } else {
                // INVALID OPCODE, DO NOTHING
                System.out.println("Unrecognised opcode");
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
        List<String> operands = getTokens(line);

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


    public void saveToFile(String filename) {
        String outputToFile;
        Ball ball;

        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for (Gizmo currentGizmo: model.getGizmos()) {
                outputToFile = currentGizmo.getType() + " " + currentGizmo.getID() + " " + currentGizmo.getXPos() + " " + currentGizmo.getYPos();
                if(currentGizmo.getType().equals("absorber")){
                    outputToFile = outputToFile + " " + ((Absorber)currentGizmo).getXPos2() + " " + ((Absorber)currentGizmo).getYPos2();
                }
                outputToFile = outputToFile.substring(0,1).toUpperCase() + outputToFile.substring(1);
                writer.println(outputToFile);
                if(currentGizmo.getType().equals("triangle")){
                    for(int i = 0; i < ((TriangularBumper)currentGizmo).getRotation(); i++){
                        writer.println("Rotate "+currentGizmo.getID());
                    }
                }
            }
            ball = model.getBall();
            writer.println("Ball " + ball.getID() + " " + ball.getXpos() + " " + ball.getYpos() + " " + ball.getVelocity().x() + " " + ball.getVelocity().y());

            // Gizmo Connections
            for(Gizmo currentGizmoKey: model.getGizmoConnections().keySet()){
                ArrayList<Gizmo> currentGizmos = model.getGizmoConnections().get(currentGizmoKey);
                for(int i = 0; i < currentGizmos.size(); i++){
                    writer.println("Connect "+currentGizmoKey.getID()+" "+currentGizmos.get(i).getID());
                }
            }

            writer.println("Gravity "+model.getGravity());
            writer.println("Friction "+model.getMu()+" "+model.getMu2());

            writer.close();

        }catch(IOException iox){
            System.out.println("IOException attempting to save to '"+filename+"': possible UnsupportedEncodingException, try printing stack trace");
        }
    }
}
