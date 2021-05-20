package hadt.example.roomwordinsert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private List<Word> mWords;
    private OnClickItem listener;

    Context context;

    public WordListAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView, listener);
    }
public void itemSetOnClick(OnClickItem listener){
        this.listener= listener;
}
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.bindData(mWords.get(position));

    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public void setmWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        Word word;

        public WordViewHolder(@NonNull View itemView, OnClickItem listener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.ClickItem(getAdapterPosition(), mWords.get(getAdapterPosition()));
                }
            });
            /**
             *neu gan su kien onclick thi gan trong ham khoi tao k gan trong bindView
             */

        }

        public void bindData(Word word) {
            word = word;
            wordItemView.setText(word.word.toString());
        }
    }
}
