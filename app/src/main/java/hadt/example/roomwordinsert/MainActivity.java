package hadt.example.roomwordinsert;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE =1 ;
    /**
     * view chi co tương tác với ViewModel
     *
     * @param savedInstanceState
     */
    private WordViewModel mWordViewModel;
    public WordListAdapter adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        

        //lay viewModel từ viewModelProvider
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        /**
         *  them livedata tra ve getAll...
         *  onChange() chay khi quan sast thấy có data thay đổi và activity đang ở Foreground
         */

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                Log.d("Word", "View:" + words.toString());
                adapter.setmWords(words);
            }
        });

        adapter.itemSetOnClick(new OnClickItem() {
            @Override
            public void ClickItem(int position, Word word) {
                Log.d(TAG, "ClickItem: "+position);
                Log.d(TAG, "ClickItem: "+word.word);
                mWordViewModel.delete(word);
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            Word word= new Word(data.getStringExtra("word"));
            mWordViewModel.insert(word);

        }else{
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
    }


}