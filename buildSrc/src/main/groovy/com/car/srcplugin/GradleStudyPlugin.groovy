package com.car.srcplugin


import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义插件
 */
class GradleStudyPlugin implements Plugin<Project> {

    Project mProject = null;
    /**
     * 插件被引入时要执行的方法
     * @param project 引入当前插件的 project
     */
    @Override
    void apply(Project project) {
        mProject = project
        def android = mProject.extensions.android

        //通过在外边定义 upDataInfo 闭包，完成对 UpDataInfoExtension 的初始化
        project.extensions.create('upDataInfo', UpDataInfoExtension)
        //创建 Task
        UpDataInfoTask task = project.tasks.create('upDataInfoTask', UpDataInfoTask)
        //配置阶段完成后执行
        mProject.afterEvaluate {
            String buildType = mProject.extensions.upDataInfo.buildType
            //默认 Release
            if (buildType == null || buildType.isEmpty()) {
                buildType = "Release"
            }
            mProject.logger.error("------------------- ${buildType} -------------------")
            def buildTask = mProject.tasks.getByName("assemble${buildType}")
            if (buildTask == null) {
                throw GradleException('the build task is not fond')
            }
            buildTask.doLast {
                task.upDataInfo()
            }
        }

    }
}