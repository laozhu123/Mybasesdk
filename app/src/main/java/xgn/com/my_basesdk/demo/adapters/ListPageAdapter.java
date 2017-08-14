package xgn.com.my_basesdk.demo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import xgn.com.my_basesdk.R;
import xgn.com.my_basesdk.demo.beans.SimpleBean;
import xgn.com.my_basesdk.demo.vholders.ListPageViewHolder;

/**
 * Created by huluzi on 2017/8/14.
 */

public class ListPageAdapter extends RecyclerView.Adapter<ListPageViewHolder> {

    private LinkedList<SimpleBean> mList;
    private EmptyListener emptyListener;

    public ListPageAdapter(LinkedList<SimpleBean> mList) {
        this.mList = mList;
    }

    public void setEmptyListener(EmptyListener emptyListener) {
        this.emptyListener = emptyListener;
    }

    @Override
    public ListPageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_page, parent, false);
        return new ListPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPageViewHolder holder, int position) {
        holder.text.setText(mList.get(position).data);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateList() {
        if (mList.size() == 0)
            emptyListener.empty();
        notifyDataSetChanged();
    }

    public interface EmptyListener {
        void empty();
    }
}
