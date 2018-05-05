package com.github.konifar.gradle.remover.remover.filetype

import spock.lang.Specification

class AnimFileRemoverTest extends Specification {

    def remover = new com.github.konifar.gradle.remover.remover.filetype.AnimFileRemover()

    def "type is anim"() {
        expect:
        remover.fileType == "anim"
    }

    def "pattern matches"() {
        def pattern = remover.createSearchPattern("fade_transition")
        def isMatched = false
        if (fileText =~ pattern) {
            isMatched = true
        }

        expect:
        isMatched == expected

        where:
        fileText                       | expected
        "R.anim.fade_transition"       | true
        "R.anim.fade_transition)"      | true
        "R.anim.fade_transition\""     | true
        "@anim/fade_transition\""      | true
        "@anim/fade_transition<"       | true
        "R.animator.fade_transition\"" | false
        "@anim/fade_transition2"       | false
        "R.anim.scale_transition"      | false
        "@animator/fade_transition"    | false
    }
}