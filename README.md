libjaclaff
==========

libjaclaff is a binary instrumentation framework for java class files. the main purpose is the create an abstract representation of the class file format. it is able to parse every type of possible class file and offers a easy way to access all relevant data.

the project also contains a byte code disassembler for java byte code.

as long as there is no api documentation please have a look at com.mitp0sh.jaclaff.test.Test0 class. the sample shows how to parse a binary class file in order to create a vcf-object ( virtual class file - object )

modifications can be done on the vcf-object and afterwards be recompiled by the framework. also described in the Test0 class sample.

the project is not finished yet and far away from being feature complete, but anyway I have it on stock for now more than 5 years, so i decided to release it.

maybe somebody can use it for research.

Good luck!

mitp0sh