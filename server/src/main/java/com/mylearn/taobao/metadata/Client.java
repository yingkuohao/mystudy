package com.mylearn.taobao.metadata;

import com.mylearn.taobao.metadata.filter.AuthCommand;
import com.mylearn.taobao.metadata.filter.CharCommand;
import com.mylearn.taobao.metadata.filter.SafeCommand;
import com.mylearn.taobao.metadata.predeal.PreDealCommand;
import com.mylearn.taobao.metadata.rules.BuildingAuthRule;
import com.mylearn.taobao.metadata.rules.BuildingPreDealRule;
import com.mylearn.taobao.metadata.rules.BuildingSafeRule;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/8
 * Time: 17:19
 * CopyRight: taobao
 * Descrption:
 */

public class Client {

    public static void main(String[] args) {
        //1.拿到目标对象，构造输入对象
        Context<FangbuildingSnapDo> context = new Context<FangbuildingSnapDo>();
        FangbuildingSnapDo fangbuildingSnapDo=new FangbuildingSnapDo();
        fangbuildingSnapDo.setBuildingId(123l);
        fangbuildingSnapDo.setBuildingName("test楼盘");
        context.setObj(fangbuildingSnapDo);

        //2.构造处理逻辑： 初始化各种command，建立command与rule的关联
        Command authCommand = new AuthCommand();
        BuildingAuthRule buildingAuthRule = new BuildingAuthRule();
        authCommand.setRule(buildingAuthRule);

        Command safeCommand = new SafeCommand();
        BuildingSafeRule buildingSafeRule = new BuildingSafeRule();
        safeCommand.setRule(buildingSafeRule);

        PreDealCommand preDealCommand = new PreDealCommand();
        BuildingPreDealRule buildingPreDealRule = new BuildingPreDealRule();
        preDealCommand.setRule(buildingPreDealRule);

        //建立command链条
        authCommand.setNextCommand(safeCommand);
        safeCommand.setNextCommand(preDealCommand);

        //3. 执行
        authCommand.execute(context);
        //4. 输出
        System.out.println("context"+context.toString());
        FangbuildingSnapDo obj =context.getObj();

    }

    static class FangbuildingSnapDo{

        private Long buildingId;
        private String buildingName;

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public Long getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(Long buildingId) {
            this.buildingId = buildingId;
        }
    }
}
