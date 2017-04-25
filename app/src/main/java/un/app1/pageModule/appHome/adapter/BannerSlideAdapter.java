package un.app1.pageModule.appHome.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import un.app1.BR;
import un.app1.R;
import un.app1.databinding.ItemBannerSlideBinding;
import un.app1.pageModule.appHome.HomeView;
import un.app1.pageModule.appHome.model.ArrayBanner;

public class BannerSlideAdapter extends RecyclerView.Adapter<BannerSlideAdapter.BindingHolder> {

    private List<ArrayBanner> bannerList;

    private HomeView homeView;

    public BannerSlideAdapter() {
        this.bannerList = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_slide, parent, false);
        return new BindingHolder(v);
    }

    public void onBindViewHolder(final BindingHolder holder, int position) {
        final ArrayBanner arrayBanner = bannerList.get(holder.getAdapterPosition());
        holder.getBinding().setVariable(BR.arrayBanner, arrayBanner);
        holder.getBinding().setArrayBanner(arrayBanner);
        //holder.getBinding().setClickMenu(view -> homeView.goToMenuActivity(position));
    }

    @BindingAdapter({"imageUrl"})
    public void loadImage(ImageView imageView, String v) {
        Picasso.with(imageView.getContext()).load(v).into(imageView);
    }

    @SuppressWarnings("unused")
    public void setMainMenu(List<ArrayBanner> arrayBanners) {
        this.bannerList = arrayBanners;
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public void setViewData(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        ItemBannerSlideBinding binding;

        BindingHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        ItemBannerSlideBinding getBinding() {
            return binding;
        }
    }

}