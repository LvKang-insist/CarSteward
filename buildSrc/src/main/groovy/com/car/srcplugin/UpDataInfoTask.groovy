package com.car.srcplugin

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat

class UpDataInfoTask extends DefaultTask {
    private VersionInfo versionInfo

    UpDataInfoTask() {
        //设置组名和描述信息
        group = '345'
        description = 'update the release info'
    }

    /**
     * 使用 TaskAction 注解，这个方法会执行在执行阶段
     * doFirst 就是给这个方法的最前面添加代码
     * doLast 就是给这个方法的最后面添加代码
     */
    @TaskAction
    void doAction() {
        upDataInfo()
    }

    /**
     * 将 Extension 类中的信息写入到指定的文件中
     */
    void upDataInfo() {
        project.logger.error "------------------- 开始插入信息 -------------------"
        String versionNameMsg = project.extensions
                .upDataInfo.versionName
        String versionCodeMsg = project.extensions
                .upDataInfo.versionCode
        String versionInfoMsg = project.extensions
                .upDataInfo.versionInfo
        String buildType = project.extensions.
                upDataInfo.buildType

        if (buildType == null || buildType.isEmpty()) {
            buildType = "Release"
        }
        File file = project.file(buildType + ".json")
        if (file != null && !file.exists()) {
            file.createNewFile()
        }
        if (file.text != null && file.text.size() >= 0) {
            String text = file.text
            if (text != null && !text.isEmpty()) {
                versionInfo = new JsonSlurper().parseText(text)
            } else {
                versionInfo = new VersionInfo()
                versionInfo.setUpDataInfo = []
            }
            if (versionInfo.setUpDataInfo.size() > 0) {
                //如果版本号一样，使用新的
                int pos = versionInfo.setUpDataInfo.size() - 1;
                if (versionNameMsg == versionInfo.setUpDataInfo.get(pos).versionName) {
                    project.logger.error "------------------- 版本号相同，已替换 -------------------"
                    versionInfo.setUpDataInfo.remove(pos)
                }
            }
            VersionInfo.UpDataInfoBean bean = new VersionInfo.UpDataInfoBean();
            bean.versionCode = versionCodeMsg
            bean.versionName = versionNameMsg
            bean.versionInfo = versionInfoMsg
            bean.versionTime = stampToDate(System.currentTimeMillis())
            versionInfo.setUpDataInfo.add(bean)

            String json = JsonOutput.toJson(versionInfo)
            file.withWriter {
                write ->
                    write.write(JsonOutput.prettyPrint(json))
            }
            project.logger.error "------------------- 更新信息已生成 -------------------"
        }
    }

    private static String stampToDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time)
        simpleDateFormat.format(date)
    }
}

