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
import un.app1.databinding.ItemTokenListrikBinding;
import un.app1.pageModule.pageListrik.ListrikView;
import un.app1.pageModule.pageListrik.model.DenomListrik;

@SuppressWarnings("ConstantConditions")
public class ListrikAdapter extends RecyclerView.Adapter<ListrikAdapter.BindingHolder> {

    private List<DenomListrik> denomListrikLista;
    private ListrikView listrikView;
    private int mCheckedPostion = -1;
    private Context context;
    private boolean checked = true;

    public ListrikAdapter() {
        this.denomListrikLista = new ArrayList<>();
    }

    @Override
    public ListrikAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_token_listrik, parent, false);
        BindingHolder holder = new BindingHolder(v);
        initCheckedItem(holder);
        return holder;
    }

    @Override
    public int getItemCount() {
        return denomListrikLista.size();
    }

    public void onBindViewHolder(final ListrikAdapter.BindingHolder holder, final int position) {
        final DenomListrik denomListrik = denomListrikLista.get(holder.getAdapterPosition());
        holder.getBinding().setVariable(BR.denomListrik, denomListrik);
        holder.getBinding().setDenomListrik(denomListrik);

        CommonUtils.setFont(holder.getBinding().layoutCheckableParent, CommonUtils.typeface(context));

        holder.getBinding().imageCheckList.setChecked(position == mCheckedPostion);
        holder.getBinding().textCurrency.setChecked(position == mCheckedPostion);
        holder.getBinding().textDenomA.setChecked(position == mCheckedPostion);
        holder.getBinding().textDenomB.setChecked(position == mCheckedPostion);
        holder.getBinding().textKwh.setChecked(position == mCheckedPostion);
        holder.getBinding().textPpn.setChecked(position == mCheckedPostion);

        String kwh = CommonUtils.currency(denomListrik.kwh) + context.getResources().getString(R.string.strKwh);
        String adminFee = context.getResources().getString(R.string.strAdminFee) + CommonUtils.currency(denomListrik.adminFee);

        holder.getBinding().textDenomA.setText(CommonUtils.CurrencyID(denomListrik.denom));
        holder.getBinding().textDenomB.setText(CommonUtils.trimDot(denomListrik.denom));
        holder.getBinding().textKwh.setText(kwh);
        holder.getBinding().textPpn.setText(adminFee);

        holder.getBinding().setClickDenomListrik(view -> {
            onClickItem(position, holder);
            listrikView.enableClickBuy(denomListrik);
        });
    }

    private void initCheckedItem(BindingHolder holder) {
        if (checked) {
            holder.getBinding().imageCheckList.setChecked(false);
            holder.getBinding().textCurrency.setChecked(false);
            holder.getBinding().textDenomA.setChecked(false);
            holder.getBinding().textDenomB.setChecked(false);
            holder.getBinding().textKwh.setChecked(false);
            holder.getBinding().textPpn.setChecked(false);
            mCheckedPostion = -1;
        }
        checked = false;
    }

    private void onClickItem(int position, ListrikAdapter.BindingHolder holder) {
        if (position == mCheckedPostion) {
            holder.getBinding().imageCheckList.setChecked(true);
            holder.getBinding().textCurrency.setChecked(true);
            holder.getBinding().textDenomA.setChecked(true);
            holder.getBinding().textDenomB.setChecked(true);
            holder.getBinding().textKwh.setChecked(true);
            holder.getBinding().textPpn.setChecked(true);
            mCheckedPostion = position;
            notifyDataSetChanged();
        } else if (position == mCheckedPostion) {
            holder.getBinding().imageCheckList.setChecked(false);
            holder.getBinding().textCurrency.setChecked(false);
            holder.getBinding().textDenomA.setChecked(false);
            holder.getBinding().textDenomB.setChecked(false);
            holder.getBinding().textKwh.setChecked(false);
            holder.getBinding().textPpn.setChecked(false);
            mCheckedPostion = -1;
            notifyDataSetChanged();
        } else {
            holder.getBinding().imageCheckList.setChecked(true);
            holder.getBinding().textCurrency.setChecked(true);
            holder.getBinding().textDenomA.setChecked(true);
            holder.getBinding().textDenomB.setChecked(true);
            holder.getBinding().textKwh.setChecked(true);
            holder.getBinding().textPpn.setChecked(true);
            mCheckedPostion = position;
            notifyDataSetChanged();
        }
    }

    public void setDenomListrik(List<DenomListrik> denomListrikList) {
        this.denomListrikLista = denomListrikList;
        notifyDataSetChanged();
    }

    public void setViewData(ListrikView listrikView) {
        this.listrikView = listrikView;
    }

    public void setFrom(Context context) {
        this.context = context;
    }

    public void setUnCheckedFirst(boolean checked) {
        this.checked = checked;
        notifyDataSetChanged();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        ItemTokenListrikBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        ItemTokenListrikBinding getBinding() {
            return binding;
        }
    }

}