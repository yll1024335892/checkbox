# checkbox
android中listView上面的checkbox的单项和指导几个固定数量的选项
##核心的代码##
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
