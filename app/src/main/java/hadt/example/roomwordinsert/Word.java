package hadt.example.roomwordinsert;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    public String word;

    public Word(@NonNull String word) {
        this.word = word;
    }
}
