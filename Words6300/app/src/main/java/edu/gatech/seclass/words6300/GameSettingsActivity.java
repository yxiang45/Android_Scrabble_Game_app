package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class GameSettingsActivity extends AppCompatActivity {
    private EditText maxNumberOfTurns;

    private EditText numOfA, numOfB, numOfC,numOfD, numOfE, numOfF,numOfG, numOfH, numOfI,numOfJ, numOfK, numOfL,
                     numOfM, numOfN, numOfO,numOfP, numOfQ, numOfR,numOfS, numOfT, numOfU,numOfV,
                     numOfW, numOfX,numOfY, numOfZ; //number of letter
    private EditText pointsForA, pointsForB,pointsForC,pointsForD,pointsForE, pointsForF,pointsForG,pointsForH,
                     pointsForI, pointsForJ,pointsForK,pointsForL,pointsForM, pointsForN,pointsForO,pointsForP,
                     pointsForQ, pointsForR,pointsForS,pointsForT,pointsForU, pointsForV,pointsForW,pointsForX,
                     pointsForY, pointsForZ; // points for letter





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        // R.id to be set by GUI
        maxNumberOfTurns = (EditText) findViewById(R.id.maxNumOfTurns);


        numOfA = (EditText) findViewById(R.id.AF );
        numOfB = (EditText) findViewById(R.id.BF );
        numOfC = (EditText) findViewById(R.id.CF );
        numOfD = (EditText) findViewById(R.id.DF );
        numOfE = (EditText) findViewById(R.id.EF );
        numOfF = (EditText) findViewById(R.id.FF );
        numOfG = (EditText) findViewById(R.id.GF );
        numOfH = (EditText) findViewById(R.id.HF );
        numOfI = (EditText) findViewById(R.id.IF);
        numOfJ = (EditText) findViewById(R.id.JF );
        numOfK = (EditText) findViewById(R.id.KF );
        numOfL = (EditText) findViewById(R.id.LF );
        numOfM = (EditText) findViewById(R.id.MF );
        numOfN = (EditText) findViewById(R.id.NF );
        numOfO = (EditText) findViewById(R.id.OF );
        numOfP = (EditText) findViewById(R.id.PF );
        numOfQ = (EditText) findViewById(R.id.QF );
        numOfR = (EditText) findViewById(R.id.RF );
        numOfS = (EditText) findViewById(R.id.SF);
        numOfT = (EditText) findViewById(R.id.TF );
        numOfU = (EditText) findViewById(R.id.UF );
        numOfV = (EditText) findViewById(R.id.VF );
        numOfW = (EditText) findViewById(R.id.WF );
        numOfX = (EditText) findViewById(R.id.XF );
        numOfY = (EditText) findViewById(R.id.YF );
        numOfZ = (EditText) findViewById(R.id.ZF );

        pointsForA = (EditText) findViewById(R.id.AV );
        pointsForB = (EditText) findViewById(R.id.BV);
        pointsForC = (EditText) findViewById(R.id.CV );
        pointsForD = (EditText) findViewById(R.id.DV );
        pointsForE = (EditText) findViewById(R.id.EV );
        pointsForF = (EditText) findViewById(R.id.FV );
        pointsForG = (EditText) findViewById(R.id.GV );
        pointsForH = (EditText) findViewById(R.id.HV );
        pointsForI = (EditText) findViewById(R.id.IV );
        pointsForJ = (EditText) findViewById(R.id.JV );
        pointsForK = (EditText) findViewById(R.id.KV );
        pointsForL = (EditText) findViewById(R.id.LV );
        pointsForM = (EditText) findViewById(R.id.MV );
        pointsForN = (EditText) findViewById(R.id.NV );
        pointsForO = (EditText) findViewById(R.id.OV );
        pointsForP = (EditText) findViewById(R.id.PV );
        pointsForQ = (EditText) findViewById(R.id.QV );
        pointsForR = (EditText) findViewById(R.id.RV );
        pointsForS = (EditText) findViewById(R.id.SV );
        pointsForT = (EditText) findViewById(R.id.TV );
        pointsForU = (EditText) findViewById(R.id.UV );
        pointsForV = (EditText) findViewById(R.id.VV );
        pointsForW = (EditText) findViewById(R.id.WV );
        pointsForX = (EditText) findViewById(R.id.XV );
        pointsForY = (EditText) findViewById(R.id.YV );
        pointsForZ = (EditText) findViewById(R.id.ZV );

        String[][] gameSettings = new String[26][2];
        try {
            gameSettings = readSettingFromFile("gameSettings.txt");
            maxNumberOfTurns.setText(readMaxNumberTurnsFromFile("gameSettings.txt"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        numOfA.setText(gameSettings[0][1]);
        numOfB.setText(gameSettings[1][1]);
        numOfC.setText(gameSettings[2][1]);
        numOfD.setText(gameSettings[3][1]);
        numOfE.setText(gameSettings[4][1]);
        numOfF.setText(gameSettings[5][1]);
        numOfG.setText(gameSettings[6][1]);
        numOfH.setText(gameSettings[7][1]);
        numOfI.setText(gameSettings[8][1]);
        numOfJ.setText(gameSettings[9][1]);
        numOfK.setText(gameSettings[10][1]);
        numOfL.setText(gameSettings[11][1]);
        numOfM.setText(gameSettings[12][1]);
        numOfN.setText(gameSettings[13][1]);
        numOfO.setText(gameSettings[14][1]);
        numOfP.setText(gameSettings[15][1]);
        numOfQ.setText(gameSettings[16][1]);
        numOfR.setText(gameSettings[17][1]);
        numOfS.setText(gameSettings[18][1]);
        numOfT.setText(gameSettings[19][1]);
        numOfU.setText(gameSettings[20][1]);
        numOfV.setText(gameSettings[21][1]);
        numOfW.setText(gameSettings[22][1]);
        numOfX.setText(gameSettings[23][1]);
        numOfY.setText(gameSettings[24][1]);
        numOfZ.setText(gameSettings[25][1]);


        pointsForA.setText(gameSettings[0][0]);
        pointsForB.setText(gameSettings[1][0]);
        pointsForC.setText(gameSettings[2][0]);
        pointsForD.setText(gameSettings[3][0]);
        pointsForE.setText(gameSettings[4][0]);
        pointsForF.setText(gameSettings[5][0]);
        pointsForG.setText(gameSettings[6][0]);
        pointsForH.setText(gameSettings[7][0]);
        pointsForI.setText(gameSettings[8][0]);
        pointsForJ.setText(gameSettings[9][0]);
        pointsForK.setText(gameSettings[10][0]);
        pointsForL.setText(gameSettings[11][0]);
        pointsForM.setText(gameSettings[12][0]);
        pointsForN.setText(gameSettings[13][0]);
        pointsForO.setText(gameSettings[14][0]);
        pointsForP.setText(gameSettings[15][0]);
        pointsForQ.setText(gameSettings[16][0]);
        pointsForR.setText(gameSettings[17][0]);
        pointsForS.setText(gameSettings[18][0]);
        pointsForT.setText(gameSettings[19][0]);
        pointsForU.setText(gameSettings[20][0]);
        pointsForV.setText(gameSettings[21][0]);
        pointsForW.setText(gameSettings[22][0]);
        pointsForX.setText(gameSettings[23][0]);
        pointsForY.setText(gameSettings[24][0]);
        pointsForZ.setText(gameSettings[25][0]);


    }

    public void handleClick(View view) {
        switch(view.getId()) {
            case R.id.saveSettingButton:

                boolean valid = true;

                Tile[] tiles = new Tile[26];
                Integer[] freq = new Integer[26];

                // check input
                if (maxNumberOfTurns.getText().toString().isEmpty()) {
                    maxNumberOfTurns.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }

               else if (numOfA.getText().toString().isEmpty()) {
                    numOfA.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfB.getText().toString().isEmpty()) {
                    numOfB.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }



                else if (numOfC.getText().toString().isEmpty()) {
                    numOfC.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfD.getText().toString().isEmpty()) {
                    numOfD.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfE.getText().toString().isEmpty()) {
                    numOfE.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfF.getText().toString().isEmpty()) {
                    numOfF.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfG.getText().toString().isEmpty()) {
                    numOfG.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfH.getText().toString().isEmpty()) {
                    numOfH.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfI.getText().toString().isEmpty()) {
                    numOfI.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfJ.getText().toString().isEmpty()) {
                    numOfJ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfK.getText().toString().isEmpty()) {
                    numOfK.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfL.getText().toString().isEmpty()) {
                    numOfL.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfM.getText().toString().isEmpty()) {
                    numOfM.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfN.getText().toString().isEmpty()) {
                    numOfN.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfO.getText().toString().isEmpty()) {
                    numOfO.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfP.getText().toString().isEmpty()) {
                    numOfP.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfQ.getText().toString().isEmpty()) {
                    numOfQ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfR.getText().toString().isEmpty()) {
                    numOfR.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfS.getText().toString().isEmpty()) {
                    numOfS.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfT.getText().toString().isEmpty()) {
                    numOfT.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfU.getText().toString().isEmpty()) {
                    numOfU.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfV.getText().toString().isEmpty()) {
                    numOfV.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfW.getText().toString().isEmpty()) {
                    numOfW.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfX.getText().toString().isEmpty()) {
                    numOfX.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfY.getText().toString().isEmpty()) {
                    numOfY.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (numOfZ.getText().toString().isEmpty()) {
                    numOfZ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }

                else if (pointsForA.getText().toString().isEmpty()) {
                    pointsForA.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForB.getText().toString().isEmpty()) {
                    pointsForB.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForC.getText().toString().isEmpty()) {
                    pointsForC.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForD.getText().toString().isEmpty()) {
                    pointsForD.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForE.getText().toString().isEmpty()) {
                    pointsForE.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForF.getText().toString().isEmpty()) {
                    pointsForF.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForG.getText().toString().isEmpty()) {
                    pointsForG.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForH.getText().toString().isEmpty()) {
                    pointsForH.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForI.getText().toString().isEmpty()) {
                    pointsForI.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForJ.getText().toString().isEmpty()) {
                    pointsForJ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForK.getText().toString().isEmpty()) {
                    pointsForK.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForL.getText().toString().isEmpty()) {
                    pointsForL.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForM.getText().toString().isEmpty()) {
                    pointsForM.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForN.getText().toString().isEmpty()) {
                    pointsForN.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForO.getText().toString().isEmpty()) {
                    pointsForO.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForP.getText().toString().isEmpty()) {
                    pointsForP.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForQ.getText().toString().isEmpty()) {
                    pointsForQ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForR.getText().toString().isEmpty()) {
                    pointsForR.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForS.getText().toString().isEmpty()) {
                    pointsForS.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForT.getText().toString().isEmpty()) {
                    pointsForT.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForU.getText().toString().isEmpty()) {
                    pointsForU.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForV.getText().toString().isEmpty()) {
                    pointsForV.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForW.getText().toString().isEmpty()) {
                    pointsForW.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForX.getText().toString().isEmpty()) {
                    pointsForX.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForY.getText().toString().isEmpty()) {
                    pointsForY.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }


                else if (pointsForZ.getText().toString().isEmpty()) {
                    pointsForZ.setError("Invalid input");
                    Context context = getApplicationContext();
                    CharSequence text = "Input cannot be empty!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    break;
                }



                else   {
                    Integer maxTurns  = Integer.parseInt(maxNumberOfTurns.getText().toString());

                    tiles[0] = new Tile('A', Integer.parseInt(pointsForA.getText().toString()));
                    freq[0] = valueOf(numOfA.getText().toString());
                    tiles[1] = new Tile('B', Integer.parseInt(pointsForB.getText().toString()));
                    freq[1] = valueOf(numOfB.getText().toString());
                    tiles[2] = new Tile('C', Integer.parseInt(pointsForC.getText().toString()));
                    freq[2] = valueOf(numOfC.getText().toString());
                    tiles[3] = new Tile('D', Integer.parseInt(pointsForD.getText().toString()));
                    freq[3] = valueOf(numOfD.getText().toString());
                    tiles[4] = new Tile('E', Integer.parseInt(pointsForE.getText().toString()));
                    freq[4] = valueOf(numOfE.getText().toString());
                    tiles[5] = new Tile('F', Integer.parseInt(pointsForF.getText().toString()));
                    freq[5] = valueOf(numOfF.getText().toString());
                    tiles[6] = new Tile('G', Integer.parseInt(pointsForG.getText().toString()));
                    freq[6] = valueOf(numOfG.getText().toString());
                    tiles[7] = new Tile('H', Integer.parseInt(pointsForH.getText().toString()));
                    freq[7] = valueOf(numOfH.getText().toString());
                    tiles[8] = new Tile('I', Integer.parseInt(pointsForI.getText().toString()));
                    freq[8] = valueOf(numOfI.getText().toString());
                    tiles[9] = new Tile('J', Integer.parseInt(pointsForJ.getText().toString()));
                    freq[9] = valueOf(numOfJ.getText().toString());
                    tiles[10] = new Tile('K', Integer.parseInt(pointsForK.getText().toString()));
                    freq[10] = valueOf(numOfK.getText().toString());
                    tiles[11] = new Tile('L', Integer.parseInt(pointsForL.getText().toString()));
                    freq[11] = valueOf(numOfL.getText().toString());
                    tiles[12] = new Tile('M', Integer.parseInt(pointsForM.getText().toString()));
                    freq[12] = valueOf(numOfM.getText().toString());
                    tiles[13] = new Tile('N', Integer.parseInt(pointsForN.getText().toString()));
                    freq[13] = valueOf(numOfN.getText().toString());
                    tiles[14] = new Tile('O', Integer.parseInt(pointsForO.getText().toString()));
                    freq[14] = valueOf(numOfO.getText().toString());
                    tiles[15] = new Tile('P', Integer.parseInt(pointsForP.getText().toString()));
                    freq[15] = valueOf(numOfP.getText().toString());
                    tiles[16] = new Tile('Q', Integer.parseInt(pointsForQ.getText().toString()));
                    freq[16] = valueOf(numOfQ.getText().toString());
                    tiles[17] = new Tile('R', Integer.parseInt(pointsForR.getText().toString()));
                    freq[17] = valueOf(numOfR.getText().toString());
                    tiles[18] = new Tile('S', Integer.parseInt(pointsForS.getText().toString()));
                    freq[18] = valueOf(numOfS.getText().toString());
                    tiles[19] = new Tile('T', Integer.parseInt(pointsForT.getText().toString()));
                    freq[19] = valueOf(numOfT.getText().toString());
                    tiles[20] = new Tile('U', Integer.parseInt(pointsForU.getText().toString()));
                    freq[20] = valueOf(numOfU.getText().toString());
                    tiles[21] = new Tile('V', Integer.parseInt(pointsForV.getText().toString()));
                    freq[21] = valueOf(numOfV.getText().toString());
                    tiles[22] = new Tile('W', Integer.parseInt(pointsForW.getText().toString()));
                    freq[22] = valueOf(numOfW.getText().toString());
                    tiles[23] = new Tile('X', Integer.parseInt(pointsForX.getText().toString()));
                    freq[23] = valueOf(numOfX.getText().toString());
                    tiles[24] = new Tile('Y', Integer.parseInt(pointsForY.getText().toString()));
                    freq[24] = valueOf(numOfY.getText().toString());
                    tiles[25] = new Tile('Z', Integer.parseInt(pointsForZ.getText().toString()));
                    freq[25] = valueOf(numOfZ.getText().toString());



                    File path = new File(GameSettingsActivity.this.getFilesDir(), "");
                    File gameSettingsFile = new File(path, "gameSettings.txt");
                    createGameSettingsFile(gameSettingsFile, maxTurns, tiles, freq);

                    AlertDialog.Builder dialog  = new AlertDialog.Builder(GameSettingsActivity.this);
                    dialog.setTitle(" ");
                    String settings = "Game Settings SAVED";
                    dialog.setMessage(settings);
                    dialog.setPositiveButton("Close",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            });
                    dialog.setCancelable(true);
                    dialog.create().show();


                }

                break;

            case R.id.backToHomeFromSettingsButton:
                startActivity(new Intent(this, Words6300.class));
                break;
        }
    }



    // Create gameSettings file with customized max number of turns/letter pool
    private void createGameSettingsFile(File gameSettingsFile, Integer maxTurns, Tile[] tiles, Integer[] freq) {
        try
        {
            FileWriter writer = new FileWriter(gameSettingsFile, false);
            writer.append(maxTurns + "\n");
            for (int i = 0; i < 26; ++i) {
                writer.append(tiles[i].getLetter() + "," + tiles[i].getPoints() + "," + freq[i]);

                writer.append("\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String[][] readSettingFromFile(String fileName) throws FileNotFoundException {
        File path = new File(GameSettingsActivity.this.getFilesDir(), "");
        File file = new File(path, fileName);
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        scanner.nextLine(); // maxNumOfTurns or currentTurns
        String[][] gameSettings = new String[26][2];
        for (int i = 0; i < 26; ++i) {
            String line = scanner.nextLine();
            String[] setting = line.split(",");
            //assume gameSetting.txt always from A to Z alphabetically with format "A,3,4"
            String[] temp = new String[2];
            temp[0] =  setting[1] ; //points
            temp[1] =  setting[2] ; //frequency
            gameSettings[i] = temp;

        }

        return gameSettings;
    }

    public String readMaxNumberTurnsFromFile(String fileName) throws FileNotFoundException {
        File path = new File(GameSettingsActivity.this.getFilesDir(), "");
        File file = new File(path, fileName);
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        return scanner.nextLine(); // maxNumOfTurns or currentTurns
    }


}