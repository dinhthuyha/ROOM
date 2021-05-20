package hadt.example.roomwordinsert;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class NewWordActivity extends AppCompatActivity {
    private EditText mEditWord;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWord= findViewById(R.id.edit_word);

       button= findViewById(R.id.button_save);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent reply= new Intent();
               if(TextUtils.isEmpty(mEditWord.getText())){
                   setResult(RESULT_CANCELED,reply);
               }else{
                   String word=  mEditWord.getText().toString();
                   reply.putExtra("word",word);
                   setResult(RESULT_OK,reply);
               }
              finish();
           }
       });
    }
}