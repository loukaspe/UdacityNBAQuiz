package com.example.android.nbaquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int RIGHT_ANSWER = 1;
    final int WRONG_ANSWER = 0;
    final int FIFTH_QUESTION_RIGHT_ANSWER = 100;
    final int TOTAL_QUESTIONS = 8;
    final int FIFTH_QUESTION_WRONG_VALUE = -1;

    // Variable for the seekBar Listener
    int progress = 0;

    // First question
    private EditText firstEditText;
    private String firstQuestionAnswer;
    // Second question
    private RadioButton secondQuestionRadioButtonOne;
    private RadioButton secondQuestionRadioButtonTwo;
    private RadioButton secondQuestionRadioButtonThree;
    // Third question
    private CheckBox thirdQuestionCheckBoxOne;
    private CheckBox thirdQuestionCheckBoxTwo;
    private CheckBox thirdQuestionCheckBoxThree;
    // Fourth question
    private SeekBar seekBar;
    private TextView textView;
    // Fifth Question
    private EditText fifthEditText;
    private int fifthQuestionAnswer = FIFTH_QUESTION_WRONG_VALUE;   // start value to determine if a number hasnt be given by the user
    // Sixth question
    private RadioButton sixthQuestionRadioButtonOne;
    private RadioButton sixthQuestionRadioButtonTwo;
    private RadioButton sixthQuestionRadioButtonThree;
    // Seventh question
    private RadioButton seventhQuestionRadioButtonOne;
    private RadioButton seventhQuestionRadioButtonTwo;
    // Eighth question
    private CheckBox eighthQuestionCheckBoxOne;
    private CheckBox eighthQuestionCheckBoxTwo;
    private CheckBox eighthQuestionCheckBoxThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fourth question
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        textView = (TextView) findViewById(R.id.progress_bar_value);

        // Initialize the textview with '0'
        textView.setText(String.valueOf(seekBar.getProgress()));

        // listener for the seekBar
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            @Override

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Display the value in textview
                textView.setText(String.valueOf(progress));
            }
        });

    }

    // Code for saving InstanceState for when rotating the screen the seekBar doesnt go missing
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
            super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putInt("progress", seekBar.getProgress());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        textView.setText(String.valueOf(savedInstanceState.getInt("progress")));
    }

    /*
     * submitScore method that calls each check*Question and adds their return values
     * then it shows the score the user got with a Toast, and a comment about it
     */
    public void submitScore(View view)
    {
        int finalScore = 0;
        finalScore += checkFirstQuestion();
        finalScore += checkSecondQuestion();
        finalScore += checkThirdQuestion();
        finalScore += checkFourthQuestion();
        finalScore += checkFifthQuestion();
        finalScore += checkSixthQuestion();
        finalScore += checkSeventhQuestion();
        finalScore += checkEighthQuestion();

        Toast.makeText(getApplicationContext(), getString(R.string.score_text, finalScore, TOTAL_QUESTIONS), Toast.LENGTH_LONG).show();

        if(finalScore == TOTAL_QUESTIONS)
            Toast.makeText(getApplicationContext(), getString(R.string.excellent_text), Toast.LENGTH_LONG).show();
        else if(finalScore  >= TOTAL_QUESTIONS - 3)
            Toast.makeText(getApplicationContext(), getString(R.string.good_text), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), getString(R.string.bad_text), Toast.LENGTH_LONG).show();
    }

    /*
     * checkFirstQuestion method that checks whether the given text
     * in the first editText is right, and returns 1 if true
     * Right Answer is National Basketball Association
     */
    private int checkFirstQuestion()
    {
        firstEditText = (EditText) findViewById(R.id.nba_word_text);
        firstQuestionAnswer = firstEditText.getText().toString().trim();    // the trim() addition is so the space characters are
                                                                            // cut from the beginning and the end
            if(firstQuestionAnswer.equals(getResources().getString(R.string.guestion_one_right_answer_one))
                    || firstQuestionAnswer.equals(getResources().getString(R.string.guestion_one_right_answer_two))
                    || firstQuestionAnswer.equals(getResources().getString(R.string.guestion_one_right_answer_three)))
                return RIGHT_ANSWER;
            else
                return WRONG_ANSWER;
    }

    /*
     * checkSecondQuestion method that checks whether the right radio button is checked
     * and returns 1 if true
     * Right Answer is button no 3 - Kareem Abdul Jabbar
     */
    private int checkSecondQuestion()
    {
        secondQuestionRadioButtonOne = (RadioButton) findViewById(R.id.michael_jordan_radio_button);
        secondQuestionRadioButtonTwo = (RadioButton) findViewById(R.id.kobe_bryant_radio_button);
        secondQuestionRadioButtonThree = (RadioButton) findViewById(R.id.abdul_jabbar_radio_button);

        if(secondQuestionRadioButtonThree.isChecked()
                && !secondQuestionRadioButtonTwo.isChecked()
                && !secondQuestionRadioButtonOne.isChecked())
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }

    /*
     * checkThirdQuestion method that checks whether the right checkBox(s) is checked
     * and returns 1 if true
     * Right Answer are checkBox no 1  and 3
     */
    private int checkThirdQuestion()
    {
        thirdQuestionCheckBoxOne = (CheckBox) findViewById(R.id.kyrie_irving_checkbox);
        thirdQuestionCheckBoxTwo = (CheckBox) findViewById(R.id.tim_duncan_checkbox);
        thirdQuestionCheckBoxThree = (CheckBox) findViewById(R.id.dirk_nowitzki_checkbox);

        if(thirdQuestionCheckBoxThree.isChecked()
                && !thirdQuestionCheckBoxTwo.isChecked()
                && thirdQuestionCheckBoxOne.isChecked())
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }

    /*
     * checkFourthQuestion method that checks whether the seekBar is in the
     * right number and returns 1 if true
     * Right Answer is number 30
     */
    private int checkFourthQuestion()
    {
        if(seekBar.getProgress() == 30)
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }

    /*
     * checkFifthQuestion method that checks whether the given number
     * in the second editText is right, and returns 1 if true
     * Right Answer is 100
     */
    private int checkFifthQuestion()
    {
        fifthEditText = (EditText) findViewById(R.id.most_points_word_text);
        String tmp = fifthEditText.getText().toString();    // temp string to be checked so that there is no
                                                            // problem with the parseInt
        if(!tmp.isEmpty())
            fifthQuestionAnswer = Integer.parseInt(tmp);

        if(fifthQuestionAnswer != FIFTH_QUESTION_WRONG_VALUE)
        {
            if(fifthQuestionAnswer == FIFTH_QUESTION_RIGHT_ANSWER)
                return RIGHT_ANSWER;
            else
                return WRONG_ANSWER;
        }
        else
            return WRONG_ANSWER;        // which means the user hasnt given an answer so its wrong
    }

    /*
     * checkSixthQuestion method that checks whether the right radio button is checked
     * and returns 1 if true
     * Right Answer is button no 1 - Boston Celtics
     */
    private int checkSixthQuestion()
    {
        sixthQuestionRadioButtonOne = (RadioButton) findViewById(R.id.celtics_radio_button);
        sixthQuestionRadioButtonTwo = (RadioButton) findViewById(R.id.lakers_radio_button);
        sixthQuestionRadioButtonThree = (RadioButton) findViewById(R.id.warriors_most_radio_button);

        if(!sixthQuestionRadioButtonThree.isChecked()
                && !sixthQuestionRadioButtonTwo.isChecked()
                && sixthQuestionRadioButtonOne.isChecked())
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }

    /*
     * checkSeventhQuestion method that checks whether the right radio button is checked
     * and returns 1 if true
     * Right Answer is button no 1 - Golden State Warriors
     */
    private int checkSeventhQuestion()
    {
        seventhQuestionRadioButtonOne = (RadioButton) findViewById(R.id.warriors_current_radio_button);
        seventhQuestionRadioButtonTwo = (RadioButton) findViewById(R.id.cavaliers_radio_button);

        if(seventhQuestionRadioButtonOne.isChecked()
                && !seventhQuestionRadioButtonTwo.isChecked())
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }

    /*
     * checkEighthQuestion method that checks whether the right checkBox(s) is checked
     * and returns 1 if true
     * Right Answer are checkBox no 2  and 3
     */
    private int checkEighthQuestion()
    {
        eighthQuestionCheckBoxOne = (CheckBox) findViewById(R.id.steph_curry_checkbox);
        eighthQuestionCheckBoxTwo = (CheckBox) findViewById(R.id.lebron_james_checkbox);
        eighthQuestionCheckBoxThree = (CheckBox) findViewById(R.id.shaquille_oneal_checkbox);

        if(eighthQuestionCheckBoxThree.isChecked()
                && eighthQuestionCheckBoxTwo.isChecked()
                && !eighthQuestionCheckBoxOne.isChecked())
            return RIGHT_ANSWER;
        else
            return WRONG_ANSWER;
    }
}
