
===Overview===

_Despite the current url, this project is also named *uima-annotation-mapper*, *uima-annotation-pattern-mapper*, and lately *uima-mapper*_.

This project offers a software solution to handle two issues the users of the [http://uima.apache.org Apache UIMA] architecture are faced.
  * Analysis is one of the major NLP tasks. A *semantic rule-based analyser* should enable to create (we say also _mapping_), update or delete annotations according to rules expressed over other annotation patterns.
  * One of the major issues dealing with any workflow management frameworks is the *components interoperability*. UIMA components only exchange data. So the data structure of the shared data is important since it ensures the interoperability. UIMA offers mechanisms to freely define its own data structure (called the _type systems_ ) and the mean to handle it afterwards. This may lead to some interoperability problems since anyone can design its own schema to represent a common concept. For example, _word_, _mot_ or _token_ can be different names to mean the same type of information. 


This project results in a Analysis Engine (AE) which requires the parameter setting of the rules file paths.
A rule declares some edit operations (such as creation) by specifying constraints over an annotations pattern and over the annotations. On creation operation it is possible to set features with values imported from the matched pattern.
Currently the pattern can count only one annotation and only the creation operation is supported. As a matter of fact, this AE was first a proof of concept.
The respect of the semantic coherence of the transformation is up to the user.

*uima-mapper* addresses the interoperability problem by inserting between two components using the same concepts unfortunately defined with distinct TS, a specific AE which is able to translate annotations from the former TS to annotions to the latter TS. 
Incidentally, the mapping operations and the constraint engine can be considered as recognizing system over annotations.

The implementation is based on  
  * [http://www.w3.org/TR/xpath/ W3C XPath] as  the language to support the declaration of constraints over the source annotations 
  * [http://commons.apache.org/jxpath Apache JXPath] as the engine which processes the constraints   
  * [http://download.oracle.com/javase/6/docs/technotes/guides/reflection/index.html java.lang.reflect API] to handle in a generic way the declaration of any annotation type inherited from `uima.tcas.Annotation` 


Thanks to anyone who is using this project to let me know by email (_nicolas.hernandez   @gmail.com)_. If you use this project to support academic research, then please cite the following paper as appropriate. 

{{{
%% hal-00481459, version 1
%% http://hal.archives-ouvertes.fr/hal-00481459/en/
@inproceedings{HERNANDEZ:2010:HAL-00481459:1,
    HAL_ID = {hal-00481459},
    URL = {http://hal.archives-ouvertes.fr/hal-00481459/en/},
    title = { {B}uilding a {F}rench-speaking community around {UIMA}, gathering research, education and industrial partners, mainly in {N}atural {L}anguage {P}rocessing and {S}peech {R}ecognizing domains},
    author = {{H}ernandez, {N}icolas and {P}oulard, {F}abien and {V}ernier, {M}atthieu and {R}ocheteau, {J}{\'e}r{\^o}me},
   
    affiliation = {{L}aboratoire d'{I}nformatique de {N}antes {A}tlantique - {LINA} - {CNRS} : {UMR}6241 - {U}niversit{\'e} de {N}antes - {E}cole des {M}ines de {N}antes },
    booktitle = {{W}orkshop {A}bstracts {LREC} 2010 {W}orkshop '{N}ew {C}hallenges for {NLP} {F}rameworks' },
    pages = {p64 },
    address = {{L}a {V}alleta {M}alte },
    day = {22},
    month = {05},
    year = {2010},
    URL = {http://hal.archives-ouvertes.fr/hal-00481459/PDF/nlpf_lrec10.pdf},
}
}}}

If you want to receive notifications on major updates, please send an email to the `nicolas.hernandez`'s gmail account with the following subject:  `uima-annotation-mapper request for notifcation`.
