package com.example.administrator.checkbox;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView listview;
    private Context mContext;
    private MyAdapter myAdapter;
    private String checkType = "more";//单个数据single,more
    private int selectNum=4;//选择的数量

    private ArrayList list = new ArrayList() { // listView的内容
        {
            add("item1");
            add("item2");
            add("item3");
            add("item4");
            add("item5");
            add("item6");
            add("item7");
            add("item8");
            add("item9");
            add("item10");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        init();
    }
    public void init() {
        listview = (ListView) findViewById(R.id.lsitview);
        for (int i = 0; i < list.size(); i++) {
            checkList.add(false); // 均为未选
        }
        myAdapter = new MyAdapter(mContext, list);
        listview.setAdapter(myAdapter);
    }
    /////checkType = "single"
    private ArrayList checkList = new ArrayList(); // 判断listview单选位置
    //设置选中的位置，将其他位置设置为未选
    public void checkPosition(int position) {
        for (int i = 0; i < checkList.size(); i++) {
            if (position == i) {// 设置已选位置
                checkList.set(i, true);
            } else {
                checkList.set(i, false);
            }
        }
        myAdapter.notifyDataSetChanged();
    }
    /////checkType = "more"
    private ArrayList checkMoreList=new ArrayList();
    private List<String> checkMoreTempList=new ArrayList<>();
    public void checkMorePosition(int position) {
        for (int i = 0; i < checkList.size(); i++) {
            if (position == i) {// 设置已选位置
                checkList.set(i, true);
            } else {
                checkList.set(i, false);
            }
        }
        myAdapter.notifyDataSetChanged();
    }

    //自定义adapter
    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private  ArrayList myList;
        public MyAdapter(Context context, ArrayList myList) {
            this.inflater = LayoutInflater.from(context);
            this.myList = myList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return myList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }
        private  ViewHolder holder = null;
        @Override
        public View getView(final int position, View convertView,ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_item, null);
                holder = new ViewHolder();
                holder.txt = (TextView) convertView.findViewById(R.id.txt);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txt.setText(myList.get(position).toString());
            if(checkType=="single"){
                holder.checkBox.setChecked((boolean)checkList.get(position));
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//单击checkbox实现单选，根据状态变换进行单选设置
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        if (isChecked) {
                            checkPosition(position);
                        } else {
                            checkList.set(position, false);//将已选择的位置设为选择
                        }
                    }
                });
            }else  if(checkType=="more"){
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//单击checkbox实现单选，根据状态变换进行单选设置
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        if (isChecked) {
                            if (checkMoreTempList.size()<selectNum){
                                checkMoreTempList.add(position+"");
                            }else {
                                CheckBox cb=(CheckBox)buttonView;
                                cb.setChecked(false);
                            }
                        } else {
                            String tempI=position+"";
                            checkMoreTempList.remove(tempI);
                        }
                    }
                });
            }
            return convertView;
        }
        public final class ViewHolder {
            public TextView txt;
            public CheckBox checkBox;
        }
    }
}
