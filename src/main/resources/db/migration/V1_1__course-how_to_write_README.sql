INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10101, 1, 'zYyRDFx3e28', 52);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10102, 1, 'IgOMI4_wQ54', 56);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10103, 1, 'rP98qcD88C8', 56);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10104, 1, 'qkhZ9kTly9k', 74);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10105, 1, 'EBZxpavWMjk', 127);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10106, 1, '_GUI7GtiTys', 62);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10107, 1, 'i602s2RxWR0', 153);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10108, 1, '4sU1LZksOH4', 130);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10109, 1, 'rWGxZGRwuAM', 130);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10111, 1, '2fZTSDn1F2o', 9);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10112, 1, 'sQj7ZSTk0U0', 10);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10113, 1, '-rrim56YdmY', 40);

INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(101031, 1, 'U7sEUIMH0yI', 37);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(101061, 1, '7ZHhSSBUzoI', 124);

INSERT INTO T_QUIZ(id, type_fk, video_fk, question, success_message, failure_message) VALUES(10103, 2, 101031, 'Who might be a potential end user of documentation?', 'That''s right! Anybody who uses your code is going to be needing to use documentation. That might be you, your coworkers, or your users!', 'One or more answers weren''t correct.');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101031, 10103, 1, true, 'You');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101032, 10103, 2, true, 'Your coworkers');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101033, 10103, 3, true, 'Your users');

INSERT INTO T_QUIZ(id, type_fk, video_fk, question, success_message) VALUES(10106, 2, 101061, 'Introduction to READMEs', 'Awesome! Let''s go over why.');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101061, 10106, 1, true, "I've examied all 3 READMEs");

INSERT INTO T_QUIZ(id, type_fk, question, success_message, failure_message) VALUES(10108, 2, 'As your codebase grows, which sections might you add into your README?', 'Yes! Now let''s take a closer look together.', 'One or more answers weren''t correct.');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101081, 10108, 1, true, 'Known bugs');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101082, 10108, 2, true, 'Frequently Asked Questions');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101083, 10108, 3, false, 'Press your project has received');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101084, 10108, 4, true, 'Table of Content');
INSERT INTO T_QUIZ_CHOICE(id, quiz_fk, rank, answer, text) VALUES(101085, 10108, 5, false, 'Picture of your cat');

INSERT INTO T_QUIZ(id, type_fk, video_fk, question, description, answer, success_message, failure_message) VALUES(10111, 4, 101061, 'Write the markdown code of the following text', '#Here is your task.
This is a sample **text** to _display_', '#Here is your task.
This is a sample **text** to _display_', 'Awesome!', 'Refer to the markup syntax!');


INSERT INTO T_COURSE(id, status_fk, level_fk, title, summary, description, language, published_datetime, updated_datetime)
  VALUES(100, 2, 1, 'How to write README', 'Learn the importance of well documented code and see how to craft meaningful READMEs.', 'Documentation is an important part of the development process. Learn to write READMEs using Markdown so your code can be used by other humans!', 'en', now(), now());

INSERT INTO T_LESSON(id, course_fk, title, rank) VALUE(101, 100, 'Writing READMEs', 1);

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10101, 101, 1, 10101, 'Welcome', 1, 52);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second, description) VALUES(10102, 101, 1, 10102, 'What Is Documentation?', 2, 56, 'Check out the <a href="https://github.com/udacity/ud777-writing-readmes">Writing READMEs</a> README over on GitHub.');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, quiz_fk, title, rank) VALUES(10103, 101, 3, 10103, 10103, 'Who Is Documentation For?', 3);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10104, 101, 1, 10104, 'How Does Nija Consume Documentation?', 4, 74);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10105, 101, 1, 10105, 'Why Should Art Have Documented His Code?', 5, 127);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, quiz_fk, title, rank) VALUES(10106, 101, 3, 10106, 10106, 'Introduction To READMEs', 6);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10107, 101, 1, 10107, 'Anatomy of README', 7, 153);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, quiz_fk, title, rank) VALUES(10108, 101, 3, 10108, 10108, 'Documenting A Growing Codebase', 8);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10109, 101, 1, 10109, 'Readable READMEs with Markdown', 9, 130);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, description) VALUES(10110, 101, 2, null, 'Basic Markdown Syntax', 10, 'This is intended as a quick reference and showcase. For more complete info, see [John Gruber''s original spec](http://daringfireball.net/projects/markdown/) and the [Github-flavored Markdown info page](http://github.github.com/github-flavored-markdown/).

Note that there is also a [Cheatsheet specific to Markdown Here](./Markdown-Here-Cheatsheet) if that''s what you''re looking for. You can also check out [more Markdown tools](./Other-Markdown-Tools).

##### Table of Contents
[Headers](#headers)
[Emphasis](#emphasis)
[Lists](#lists)
[Links](#links)
[Images](#images)
[Code and Syntax Highlighting](#code)
[Tables](#tables)
[Blockquotes](#blockquotes)
[Inline HTML](#html)
[Horizontal Rule](#hr)
[Line Breaks](#lines)
[YouTube Videos](#videos)

<a name="headers"></a>

## Headers

```no-highlight
# H1
## H2
### H3
#### H4
##### H5
###### H6

Alternatively, for H1 and H2, an underline-ish style:

Alt-H1
======

Alt-H2
------
```

# H1
## H2
### H3
#### H4
##### H5
###### H6

Alternatively, for H1 and H2, an underline-ish style:

Alt-H1
======

Alt-H2
------

<a name="emphasis"></a>

## Emphasis

```no-highlight
Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~
```

Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~


<a name="lists"></a>

## Lists

(In this example, leading and trailing spaces are shown with with dots: ⋅)

```no-highlight
1. First ordered list item
2. Another item
⋅⋅* Unordered sub-list.
1. Actual numbers don''t matter, just that it''s a number
⋅⋅1. Ordered sub-list
4. And another item.

⋅⋅⋅You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we''ll use three here to also align the raw Markdown).

⋅⋅⋅To have a line break without a paragraph, you will need to use two trailing spaces.⋅⋅
⋅⋅⋅Note that this line is separate, but within the same paragraph.⋅⋅
⋅⋅⋅(This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)

* Unordered list can use asterisks
- Or minuses
+ Or pluses
```

1. First ordered list item
2. Another item
  * Unordered sub-list.
1. Actual numbers don''t matter, just that it''s a number
  1. Ordered sub-list
4. And another item.

   You can have properly indented paragraphs within list items. Notice the blank line above, and the leading spaces (at least one, but we''ll use three here to also align the raw Markdown).

   To have a line break without a paragraph, you will need to use two trailing spaces.
   Note that this line is separate, but within the same paragraph.
   (This is contrary to the typical GFM line break behaviour, where trailing spaces are not required.)

* Unordered list can use asterisks
- Or minuses
+ Or pluses

<a name="links"></a>

## Links

There are two ways to create links.

```no-highlight
[I''m an inline-style link](https://www.google.com)

[I''m an inline-style link with title](https://www.google.com "Google''s Homepage")

[I''m a reference-style link][Arbitrary case-insensitive reference text]

[I''m a relative reference to a repository file](../blob/master/LICENSE)

[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself].

URLs and URLs in angle brackets will automatically get turned into links.
http://www.example.com or <http://www.example.com> and sometimes
example.com (but not on Github, for example).

Some text to show that the reference links can follow later.

[arbitrary case-insensitive reference text]: https://www.mozilla.org
[1]: http://slashdot.org
[link text itself]: http://www.reddit.com
```

[I''m an inline-style link](https://www.google.com)

[I''m an inline-style link with title](https://www.google.com "Google''s Homepage")

[I''m a reference-style link][Arbitrary case-insensitive reference text]

[I''m a relative reference to a repository file](../blob/master/LICENSE)

[You can use numbers for reference-style link definitions][1]

Or leave it empty and use the [link text itself].

URLs and URLs in angle brackets will automatically get turned into links.
http://www.example.com or <http://www.example.com> and sometimes
example.com (but not on Github, for example).

Some text to show that the reference links can follow later.

[arbitrary case-insensitive reference text]: https://www.mozilla.org
[1]: http://slashdot.org
[link text itself]: http://www.reddit.com

<a name="images"></a>

## Images

```no-highlight
Here''s our logo (hover to see the title text):

Inline-style:
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")

Reference-style:
![alt text][logo]

[logo]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 2"
```

Here''s our logo (hover to see the title text):

Inline-style:
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 1")

Reference-style:
![alt text][logo]

[logo]: https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Logo Title Text 2"

<a name="code"></a>

## Code and Syntax Highlighting

Code blocks are part of the Markdown spec, but syntax highlighting isn''t. However, many renderers -- like Github''s and *Markdown Here* -- support syntax highlighting. Which languages are supported and how those language names should be written will vary from renderer to renderer. *Markdown Here* supports highlighting for dozens of languages (and not-really-languages, like diffs and HTTP headers); to see the complete list, and how to write the language names, see the [highlight.js demo page](http://softwaremaniacs.org/media/soft/highlight/test.html).

```no-highlight
Inline `code` has `back-ticks around` it.
```

Inline `code` has `back-ticks around` it.

Blocks of code are either fenced by lines with three back-ticks <code>```</code>, or are indented with four spaces. I recommend only using the fenced code blocks -- they''re easier and only they support syntax highlighting.

<pre lang="no-highlight"><code>```javascript
var s = "JavaScript syntax highlighting";
alert(s);
```

```python
s = "Python syntax highlighting"
print s
```

```
No language indicated, so no syntax highlighting.
But let''s throw in a &lt;b&gt;tag&lt;/b&gt;.
```
</code></pre>



```javascript
var s = "JavaScript syntax highlighting";
alert(s);
```

```python
s = "Python syntax highlighting"
print s
```

```
No language indicated, so no syntax highlighting in Markdown Here (varies on Github).
But let''s throw in a <b>tag</b>.
```


<a name="tables"></a>

## Tables

Tables aren''t part of the core Markdown spec, but they are part of GFM and *Markdown Here* supports them. They are an easy way of adding tables to your email -- a task that would otherwise require copy-pasting from another application.

```no-highlight
Colons can be used to align columns.

| Tables        | Are           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don''t need to make the
raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3
```

Colons can be used to align columns.

| Tables        | Are           | Cool |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell. The outer pipes (|) are optional, and you don''t need to make the raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3

<a name="blockquotes"></a>

## Blockquotes

```no-highlight
> Blockquotes are very handy in email to emulate reply text.
> This line is part of the same quote.

Quote break.

> This is a very long line that will still be quoted properly when it wraps. Oh boy let''s keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote.
```

> Blockquotes are very handy in email to emulate reply text.
> This line is part of the same quote.

Quote break.

> This is a very long line that will still be quoted properly when it wraps. Oh boy let''s keep writing to make sure this is long enough to actually wrap for everyone. Oh, you can *put* **Markdown** into a blockquote.

<a name="html"></a>

## Inline HTML

You can also use raw HTML in your Markdown, and it''ll mostly work pretty well.

```no-highlight
<dl>
  <dt>Definition list</dt>
  <dd>Is something people use sometimes.</dd>

  <dt>Markdown in HTML</dt>
  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>
```

<dl>
  <dt>Definition list</dt>
  <dd>Is something people use sometimes.</dd>

  <dt>Markdown in HTML</dt>
  <dd>Does *not* work **very** well. Use HTML <em>tags</em>.</dd>
</dl>

<a name="hr"></a>

## Horizontal Rule

```
Three or more...

---

Hyphens

***

Asterisks

___

Underscores
```

Three or more...

---

Hyphens

***

Asterisks

___

Underscores

<a name="lines"></a>

## Line Breaks

My basic recommendation for learning how line breaks work is to experiment and discover -- hit &lt;Enter&gt; once (i.e., insert one newline), then hit it twice (i.e., insert two newlines), see what happens. You''ll soon learn to get what you want. "Markdown Toggle" is your friend.

Here are some things to try out:

```
Here''s a line for us to start with.

This line is separated from the one above by two newlines, so it will be a *separate paragraph*.

This line is also a separate paragraph, but...
This line is only separated by a single newline, so it''s a separate line in the *same paragraph*.
```

Here''s a line for us to start with.

This line is separated from the one above by two newlines, so it will be a *separate paragraph*.

This line is also begins a separate paragraph, but...
This line is only separated by a single newline, so it''s a separate line in the *same paragraph*.

(Technical note: *Markdown Here* uses GFM line breaks, so there''s no need to use MD''s two-space line breaks.)

<a name="videos"></a>

## YouTube Videos

They can''t be added directly but you can add an image with a link to the video like this:

```no-highlight
<a href="http://www.youtube.com/watch?feature=player_embedded&v=YOUTUBE_VIDEO_ID_HERE
" target="_blank"><img src="http://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg"
alt="IMAGE ALT TEXT HERE" width="240" height="180" border="10" /></a>
```

Or, in pure Markdown, but losing the image sizing and border:

```no-highlight
[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg)](http://www.youtube.com/watch?v=YOUTUBE_VIDEO_ID_HERE)
```

Referencing a bug by #bugID in your git commit links it to the slip. For example #1.

---

License: [CC-BY](https://creativecommons.org/licenses/by/3.0/)');

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, quiz_fk, title, rank) VALUES(10111, 101, 3, 10111, 10111, 'Basic Markdown Syntax Quiz', 11);

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10112, 101, 1, 10112, 'Markdown Syntax Practice', 12, 10);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, duration_second) VALUES(10113, 101, 1, 10113, 'Document Everything!', 13, 40);
