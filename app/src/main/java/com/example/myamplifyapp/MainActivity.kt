package com.example.myamplifyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import com.amplifyframework.core.Amplify
import com.amplifyframework.predictions.models.LanguageType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Amplify.Predictions.translateText(
                "I like to eat spaghetti",
                { result -> Log.i("MyAmplifyApp", result.getTranslatedText()) },
                { error -> Log.e("MyAmplifyApp", "Translation failed", error) }
        )

        suspend fun generateTranslatedString(inputText: String): String {
            var text: String = inputText

            Amplify.Predictions.translateText(
            inputText,
            LanguageType.ENGLISH,
            LanguageType.HINDI,
            { result -> text = result.getTranslatedText() },
            { error -> Log.e("MyAmplifyApp", "Translation failed", error) }
            )

            return text
        }


        val button_text = findViewById<Button>(R.id.button2);
        button_text.setText(generateTranslatedString("Submit!!!"))


        val switch_text = findViewById<Switch>(R.id.switch1);
        switch_text.setText(generateTranslatedString("Switch between English and Hindi language."));

        val textView_text = findViewById<TextView>(R.id.textView1);
        textView_text.setText(generateTranslatedString("A banking app supporting both english and hindi."));
    }

}