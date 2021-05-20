package hadt.example.roomwordinsert;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * giao tiep voi DAO va network
 */
public class WordRepository {
    private WordDao wordDao;
    LiveData<List<Word>> mAllWords;
    private static final String TAG = "WordRepository";

    WordRepository(Application application){
        WordRoomDatabase db= WordRoomDatabase.getINSTANCE(application);
        wordDao= db.wordDao();
        mAllWords= wordDao.getAlphabetizedWords();

    }

    LiveData<List<Word>> getAllWords(){

        return mAllWords;}

    /**
     * tao ra mot thread thuc thi insert du lieu
     * @param word
     */
    public void insert(Word word){
        new insertAsyncTask(wordDao).execute(word);

    }

    public void delete(Word word){
        new deleteAyncTask(wordDao).execute(word);
    }

    private static class deleteAyncTask extends AsyncTask<Word, Void, Void>{
        WordDao dao;

        public deleteAyncTask(WordDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            dao.delete(words[0]);
            return null;
        }
    }
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

         private WordDao mWordDao;

        public insertAsyncTask(WordDao mWordDao) {
            this.mWordDao = mWordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.insert(words[0]);

            return null;
        }
    }
}
