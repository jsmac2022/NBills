package com.nictbills.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nictbills.app.R;
import com.nictbills.app.interfaces.ParentChildRecView;

import java.util.List;

public class SlotDetailsInnerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    Context context;
    private List<String> slotList;
    private ParentChildRecView myListener;
    private int selectedPosition = -1, parentPosition;
    private CallBack callBack;
    private int selectedPrentPos;


    public SlotDetailsInnerAdapter(Context context, List<String> list, ParentChildRecView myListener, int parentPosition, int chilPos, int parentSelectPos) {
        this.context = context;
        this.slotList = list;
        this.myListener = myListener;
        this.parentPosition = parentPosition;
        this.selectedPosition = chilPos;
        this.selectedPrentPos = parentSelectPos;

    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void addData(Context context, List<String> list, ParentChildRecView childRecView, int position) {
        this.context = context;
        this.slotList = list;
        this.myListener = childRecView;
        this.parentPosition = position;
        notifyDataSetChanged();
    }

    public interface CallBack {
        void refreshView(int parentPosition, int position);
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater;
        baseViewHolder = new SlotDetailsInnerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_details_inner_card, parent, false));
        return baseViewHolder;
    }


    public class ViewHolder extends BaseViewHolder {

        private TextView time_slot_BTN;
        //   private RecyclerView vaccine_details_RV;

        public ViewHolder(View itemView) {
            super(itemView);
            time_slot_BTN = (TextView) itemView.findViewById(R.id.time_slot_BTN);

        }

        protected void clear() {

        }

        public void onBind(final int position) {
            super.onBind(position);

            time_slot_BTN.setText(slotList.get(position));

            if (selectedPrentPos != -1 && selectedPrentPos==parentPosition) {
                if (selectedPosition == position) {
                    itemView.setSelected(true);
                    //using selector drawable
                    time_slot_BTN.setTextColor(Color.parseColor("#ff0000"));
                }
            }



            time_slot_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                    if (selectedPosition >= 0)
                        notifyItemChanged(selectedPosition);
                    selectedPosition = getAdapterPosition();
                    notifyItemChanged(selectedPosition);
                    myListener.onListItem(v, parentPosition, position);
                    callBack.refreshView(parentPosition, position);

                }
            });

        }

    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, final int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (slotList != null && slotList.size() > 0) {
            return slotList.size();
        } else {
            return 0;
        }
    }


}


