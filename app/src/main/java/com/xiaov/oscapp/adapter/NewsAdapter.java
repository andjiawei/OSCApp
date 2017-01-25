package com.xiaov.oscapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaov.oscapp.R;
import com.xiaov.oscapp.base.ListBaseAdapter;
import com.xiaov.oscapp.bean.News;
import com.xiaov.oscapp.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述:
 * 作者：zhangjiawei
 * 时间：2017/1/23
 */
public class NewsAdapter extends ListBaseAdapter<News> {

    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_news, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        News news = mDatas.get(position);
        vh.title.setText(news.getTitle());

        String description = news.getBody();
        vh.description.setVisibility(View.GONE);
        if (description != null && !StringUtils.isEmpty(description)) {
            vh.description.setVisibility(View.VISIBLE);
            vh.description.setText(description.trim());
        }

        vh.source.setText(news.getAuthor());
        if (StringUtils.isToday(news.getPubDate())) {
            vh.tip.setVisibility(View.VISIBLE);
        } else {
            vh.tip.setVisibility(View.GONE);
        }
        vh.time.setText(StringUtils.friendly_time(news.getPubDate()));
        vh.comment_count.setText(news.getCommentCount() + "");

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_description)
        TextView description;
        @BindView(R.id.tv_source)
        TextView source;
        @BindView(R.id.tv_time)
        TextView time;
        @BindView(R.id.tv_comment_count)
        TextView comment_count;
        @BindView(R.id.iv_tip)
        ImageView tip;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
