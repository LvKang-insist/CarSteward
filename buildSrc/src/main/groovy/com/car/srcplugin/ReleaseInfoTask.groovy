package com.car.srcplugin

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat

class ReleaseInfoTask extends DefaultTask {
    private VersionInfo versionInfo

    ReleaseInfoTask() {
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
    public void upDataInfo() {
        String versionNameMsg = project.extensions
                .releaseInfo.versionName
        String versionCodeMsg = project.extensions
                .releaseInfo.versionCode
        String versionInfoMsg = project.extensions
                .releaseInfo.versionInfo
        String fileName = project.extensions
                .releaseInfo.fileName

        File file = project.file(fileName + ".json")
        if (file != null && !file.exists()) {
            file.createNewFile()
        }
        if (file.text != null && file.text.size() >= 0) {
            String text = file.text
            if (text != null && !text.isEmpty()) {
                versionInfo = new JsonSlurper().parseText(text)
            } else {
                versionInfo = new VersionInfo()
                versionInfo.relaseInfo = new ArrayList<>()
            }
            VersionInfo.RelaseInfoBean bean = new VersionInfo.RelaseInfoBean();
            bean.versionCode = versionCodeMsg
            bean.versionName = versionNameMsg
            bean.versionInfo = versionInfoMsg
            bean.versionTime = stampToDate(System.currentTimeMillis())
            versionInfo.relaseInfo.add(bean)

            String json = JsonOutput.toJson(versionInfo)
            file.withWriter {
                write ->
                    write.write(JsonOutput.prettyPrint(json))
            }
        }
    }

    private static String stampToDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time)
        simpleDateFormat.format(date)
    }
}

