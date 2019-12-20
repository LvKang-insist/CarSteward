package com.car.srcplugin


import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义插件
 */
class GradleStudyPlugin implements Plugin<Project> {

    /**
     * 插件被引入时要执行的方法
     * @param project 引入当前插件的 project
     */
    @Override
    void apply(Project project) {
        //通过在外边定义 ReleaseInfo 闭包，完成对 ReleaseInfoExtension 的初始化
        project.extensions.create('releaseInfo', ReleaseInfoExtension)
        //创建 Task
        ReleaseInfoTask task = project.tasks.create('replaceInfoTask', ReleaseInfoTask)
        project.afterEvaluate {
            Project pro ->
                def buildTask = pro.tasks.getByName('build')
                if (buildTask == null) {
                    throw GradleException('the build task is not fond')
                }
                buildTask.doLast {
                    task.upDataInfo()
                }
        }
    }
}