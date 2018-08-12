package alexandre.thauvin.smarttoolbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alexandre.thauvin.smarttoolbox.ListFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Task> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Task> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mAction.setText(mValues.get(position).getAction());
        holder.mService.setText(mValues.get(position).getService());
        holder.mHour.setText(mValues.get(position).getHour());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mAction;
        public final TextView mService;
        public final TextView mHour;
        public Task mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHour = view.findViewById(R.id.item_hour);
            mAction =  view.findViewById(R.id.item_action);
            mService =  view.findViewById(R.id.item_service);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mService.getText() + "'";
        }
    }
}
