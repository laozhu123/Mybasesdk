package xgn.com.my_basesdk.demo.vholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import xgn.com.my_basesdk.R;

/**
 * Created by huluzi on 2017/8/14.
 */

public class ListPageViewHolder extends RecyclerView.ViewHolder {
    public TextView text;
    public ListPageViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.text);
    }
}
