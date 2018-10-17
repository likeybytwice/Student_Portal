package nl.mira.mayla.student_portal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder>{

    //variables
    private List<Portal> mPortals;
    final private PortalClickListener mPortalClickListener;

    public PortalAdapter(List<Portal> mPortals, PortalClickListener mPortalClickListener) {
        this.mPortals = mPortals;
        this.mPortalClickListener = mPortalClickListener;
    }

    //inflate layout to display the row items in the RecylcerView
    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance
        PortalAdapter.ViewHolder viewHolder = new PortalAdapter.ViewHolder(view);
        return viewHolder;
    }

    //Bind  data to the viewHolders
    @Override
    public void onBindViewHolder(PortalAdapter.ViewHolder viewHolder, int i) {
        Portal portal = mPortals.get(i);

        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        viewHolder.textView.setText(portal.getmPortalText());
    }

    @Override
    public int getItemCount() {
        return mPortals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //ViewHolder displays all the row items that are in the RecyclerView

        public TextView textView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        //When clicked on portal title in recycler view
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mPortalClickListener.portalOnClick(clickedPosition);
        }

    }

    public interface PortalClickListener{
        void portalOnClick(int i);
    }
}
