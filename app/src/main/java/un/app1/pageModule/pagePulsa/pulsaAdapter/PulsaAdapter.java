package un.app1.pageModule.pagePulsa.pulsaAdapter;

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
import un.app1.databinding.ItemDenomPulsaBinding;
import un.app1.pageModule.pagePulsa.PulsaView;
import un.app1.pageModule.pagePulsa.model.DenomPulsa;

@SuppressWarnings("ConstantConditions")
public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.BindingHolder> {

    private List<DenomPulsa> denomPulsaList;
    private PulsaView pulsaView;
    private int mCheckedPostion = -1;
    private Context context;
    private boolean checked = true;

    public PulsaAdapter() {
        this.denomPulsaList = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_denom_pulsa, parent, false);
        BindingHolder holder = new PulsaAdapter.BindingHolder(v);
        initCheckedItem(holder);
        return holder;
    }

    @Override
    public int getItemCount() {
        return denomPulsaList.size();
    }

    public void onBindViewHolder(final BindingHolder holder, final int position) {
        final DenomPulsa denomPulsa = denomPulsaList.get(holder.getAdapterPosition());
        holder.getBinding().setVariable(BR.denomListrik, denomPulsa);
        holder.getBinding().setDenomPulsa(denomPulsa);
        CommonUtils.setFont(holder.getBinding().layoutCheckableParent, CommonUtils.typeface(context));

        holder.getBinding().imageCheckList.setChecked(position == mCheckedPostion);
        holder.getBinding().textCurrency.setChecked(position == mCheckedPostion);
        holder.getBinding().textDenomA.setChecked(position == mCheckedPostion);
        holder.getBinding().textDenomB.setChecked(position == mCheckedPostion);
        holder.getBinding().textPpn.setChecked(position == mCheckedPostion);

        String adminFee = context.getResources().getString(R.string.strAdminFee) + CommonUtils.currency(denomPulsa.adminFee);

        holder.getBinding().textDenomA.setText(CommonUtils.CurrencyID(denomPulsa.denom));
        holder.getBinding().textDenomB.setText(CommonUtils.trimDot(denomPulsa.denom));
        holder.getBinding().textPpn.setText(adminFee);

        holder.getBinding().setOnClick(view -> {
            onClickItem(position, holder);
            //pulsaView.enableClickBuy(denomPulsa);
        });
    }

    private void initCheckedItem(BindingHolder holder) {
        if (checked) {
            holder.getBinding().imageCheckList.setChecked(false);
            holder.getBinding().textCurrency.setChecked(false);
            holder.getBinding().textDenomA.setChecked(false);
            holder.getBinding().textDenomB.setChecked(false);
            holder.getBinding().textPpn.setChecked(false);
            mCheckedPostion = -1;
        }
        checked = false;
    }

    private void onClickItem(int position, BindingHolder holder) {
        if (position == mCheckedPostion) {
            holder.getBinding().imageCheckList.setChecked(true);
            holder.getBinding().textCurrency.setChecked(true);
            holder.getBinding().textDenomA.setChecked(true);
            holder.getBinding().textDenomB.setChecked(true);
            holder.getBinding().textPpn.setChecked(true);
            mCheckedPostion = position;
            notifyDataSetChanged();
        } else if (position == mCheckedPostion) {
            holder.getBinding().imageCheckList.setChecked(false);
            holder.getBinding().textCurrency.setChecked(false);
            holder.getBinding().textDenomA.setChecked(false);
            holder.getBinding().textDenomB.setChecked(false);
            holder.getBinding().textPpn.setChecked(false);
            mCheckedPostion = -1;
            notifyDataSetChanged();
        } else {
            holder.getBinding().imageCheckList.setChecked(true);
            holder.getBinding().textCurrency.setChecked(true);
            holder.getBinding().textDenomA.setChecked(true);
            holder.getBinding().textDenomB.setChecked(true);
            holder.getBinding().textPpn.setChecked(true);
            mCheckedPostion = position;
            notifyDataSetChanged();
        }
    }

    public void setDenomPulsa(List<DenomPulsa> denomPulsaList) {
        this.denomPulsaList = denomPulsaList;
        notifyDataSetChanged();
    }

    public void setViewData(PulsaView pulsaView) {
        this.pulsaView = pulsaView;
    }

    public void setFrom(Context context) {
        this.context = context;
    }

    public void setUnCheckedFirst(boolean checked) {
        this.checked = checked;
        notifyDataSetChanged();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        ItemDenomPulsaBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        ItemDenomPulsaBinding getBinding() {
            return binding;
        }
    }

}
