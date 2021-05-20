package hadt.example.roomwordinsert;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    /**
     * viewmodel giao tiep voi repository va view
     */
    private WordRepository mRepository;
    LiveData<List<Word>> mAllWords;
    private static final String TAG = "WordViewModel";
    Context context;

    public WordViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();

        ;
    }

    LiveData<List<Word>> getAllWords() {

        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void delete(Word word) {
        mRepository.delete(word);
    }
}
