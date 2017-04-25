package un.app1.pageModule.pageListrik.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import un.app1.BR;
import un.app1.R;
import un.app1.appCommon.common.CommonUtils;
import un.app1.databinding.ItemHistoryListrikBinding;
import un.app1.pageModule.pageListrik.model.HistoryPayment;

@SuppressWarnings("ConstantConditions")
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.BindingHolder> {

    private List<HistoryPayment> historyPaymentList;
    private Context context;

    public HistoryAdapter() {
        this.historyPaymentList = new ArrayList<>();
    }

    @Override
    public HistoryAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_listrik, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public int getItemCount() {
        return historyPaymentList.size();
    }

    public void onBindViewHolder(final HistoryAdapter.BindingHolder holder, final int position) {
        final HistoryPayment historyPayment = historyPaymentList.get(holder.getAdapterPosition());
        holder.getBinding().setVariable(BR.historyPayment, historyPayment);
        holder.getBinding().setHistoryPayment(historyPayment);
        CommonUtils.setFont(holder.getBinding().cardOrder, CommonUtils.typeface(context));

        String total = context.getResources().getString(R.string.strRp) + CommonUtils.currency(historyPayment.totalPayment);

        holder.getBinding().textAmount.setText(CommonUtils.currency(historyPayment.amount));
        holder.getBinding().textAdminFee.setText(CommonUtils.currency(historyPayment.adminFee));
        holder.getBinding().textTotal.setText(total);
        holder.getBinding().TextStatus.setText(historyPayment.statusPayment);

    }

    public void setHistoryPayments(List<HistoryPayment> historyPaymentList) {
        this.historyPaymentList = historyPaymentList;
        notifyDataSetChanged();
    }

    public void setFrom(Context context) {
        this.context = context;
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        ItemHistoryListrikBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        ItemHistoryListrikBinding getBinding() {
            return binding;
        }
    }

}