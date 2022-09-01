package com.qmdroider.tictactoe

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textView: TextView
    private lateinit var textView1: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var textView4: TextView
    private lateinit var textView5: TextView
    private lateinit var textView6: TextView
    private lateinit var textView7: TextView
    private lateinit var textView8: TextView
    private lateinit var textView9: TextView

    private var textViewId: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up view model
        val viewModel: GameViewModel by viewModels()

        //setup observers
        viewModel.stringCurrentTurnText.observe(this, Observer<String> {stringTurnText ->
            textViewTitle.text = stringTurnText
        })

        //observe resId of clicked textView
        viewModel.intCurrentTextViewId.observe(this, Observer<Int> {id ->
            textViewId = id
        })

        //update texView with above resId with X or O based on observed data below
        viewModel.stringCurrentTextViewXO.observe(this, Observer<String> {stringXO ->
            textView = findViewById(textViewId)
            textView.text = stringXO
        })


        //reset all textViews when game has ended
        viewModel.boolResetBoard.observe(this, Observer<Boolean> {boolReset ->
            if(boolReset) {
                textView1.text = ""
                textView2.text = ""
                textView3.text = ""
                textView4.text = ""
                textView5.text = ""
                textView6.text = ""
                textView7.text = ""
                textView8.text = ""
                textView9.text = ""
            }
        })

        //display winner when stringWinner gets updated in viewModel
        viewModel.stringWinner.observe(this, Observer<String> {stringWinner ->
            if(stringWinner != "") {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this)
                // set message of alert dialog
                dialogBuilder.setMessage(stringWinner)
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Play Again", DialogInterface.OnClickListener {
                            _, _ -> viewModel.reset()
                        })
                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Game Over")
                // show alert dialog
                alert.show()
            }

        })

        //connect textView vars to layout
        textViewTitle = findViewById(R.id.textViewTitle)

        textView1 = findViewById(R.id.textView1)
        textView1.tag = 1

        textView2 = findViewById(R.id.textView2)
        textView2.tag = 2

        textView3 = findViewById(R.id.textView3)
        textView3.tag = 3

        textView4 = findViewById(R.id.textView4)
        textView4.tag = 4

        textView5 = findViewById(R.id.textView5)
        textView5.tag = 5

        textView6 = findViewById(R.id.textView6)
        textView6.tag = 6

        textView7 = findViewById(R.id.textView7)
        textView7.tag = 7

        textView8 = findViewById(R.id.textView8)
        textView8.tag = 8

        textView9 = findViewById(R.id.textView9)
        textView9.tag = 9

        //setup and attach listener
        val listener = View.OnClickListener { v ->
            viewModel.textViewPressed((v as TextView).id, (v as TextView).tag as Int)
        }

        textView1.setOnClickListener(listener)
        textView2.setOnClickListener(listener)
        textView3.setOnClickListener(listener)
        textView4.setOnClickListener(listener)
        textView5.setOnClickListener(listener)
        textView6.setOnClickListener(listener)
        textView7.setOnClickListener(listener)
        textView8.setOnClickListener(listener)
        textView9.setOnClickListener(listener)
    }

    override fun onStart() {
        super.onStart()
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()
    }
}