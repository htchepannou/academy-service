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
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10113, 1, 'sQj7ZSTk0U0', 10);
INSERT INTO T_VIDEO(id, type_fk, video_id, duration_second) VALUES(10114, 1, 'rrim56YdmY', 40);


INSERT INTO T_COURSE(id, status_fk, level_fk, title, summary, description, language, published_datetime, updated_datetime)
  VALUES(100, 2, 1, 'How to write README', 'Learn the importance of well documented code and see how to craft meaningful READMEs.', 'Documentation is an important part of the development process. Learn to write READMEs using Markdown so your code can be used by other humans!', 'en', now(), now());

INSERT INTO T_LESSON(id, course_fk, title, rank) VALUE(101, 100, 'Writing READMEs', 1);

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10101, 101, 1, 10101, 'Welcome', 1);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, description) VALUES(10102, 101, 1, 10102, 'What Is Documentation?', 2, 'Check out the <a href="https://github.com/udacity/ud777-writing-readmes">Writing READMEs</a> README over on GitHub.');
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10103, 101, 3, 10104, 'Quiz: Who Is Documentation For?', 3);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10104, 101, 1, 10104, 'How Does Nija Consume Documentation?', 4);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10105, 101, 1, 10105, 'Why Should Art Have Documented His Code?', 5);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10106, 101, 3, 10106, 'Quiz: Introduction To READMEs', 6);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10107, 101, 1, 10107, 'Anatomy of README', 7);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10108, 101, 3, 10108, 'Quiz: Documenting A Growing Codebase', 9);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10109, 101, 1, 10109, 'Readable READMEs with Markdown', 9);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, description) VALUES(10110, 101, 2, null, 'Basic Markdown Syntax', 10, '<h1 id="markdown-101">Markdown 101</h1>
<p>Markdown is a light markup language often used for READMEs (though you''ll find other use cases for it, too!). It is fairly straightforward, and much of the syntax is intuitive.</p>
<p>But as it turns out, there are many different <em>dialects</em> of Markdown, just like in a spoken language. Each of these dialects is known as a <em>flavor</em> of Markdown.
Most of these dialects are pretty much the same, with only minor differences.</p>
<p>Since your READMEs will ultimately end up on GitHub, we''ll be using <strong>GitHub Flavored Markdown</strong>.
I''ve included a link to the full documentation for it in the instructor notes (and we''ll be using that later in this course), but I''ll get you started with a quick crash course.</p>
<h2 id="feeling-bold-">Feeling Bold?</h2>
<p>To make text <strong>bold</strong>, surround it with double asterisks. So this code:</p>
<pre><code>Isn''t today a **wonderful** <span class="hljs-property">day</span>?
</code></pre><p>becomes: Isn''t today a <strong>wonderful</strong> day?</p>
<p>This reads a bit more nicely than a <code>&lt;strong&gt;</code> tag would in HTML, and takes considerably fewer keystrokes to type out.</p>
<h2 id="_italics_-i-tell-you-"><em>Italics</em>, I tell you!</h2>
<p>To <em>italicize</em> text, surround it with underscores. So this code:</p>
<pre><code>And <span class="hljs-keyword">in</span> <span class="hljs-keyword">that</span> moment I thought <span class="hljs-keyword">to</span> myself: _Did I turn off <span class="hljs-keyword">the</span> stove?_
</code></pre><p>becomes: And in that moment I thought to myself: <em>Did I turn off the stove?</em></p>
<p>Much like the previous example, this code reads much more like English, which is great for when you''re skimming the original document.</p>
<h2 id="to-code-or-not-to-code-">To <code>code</code>, or not to <code>code</code>?</h2>
<p>Inline <code>code</code> is useful for indicating that you''re writing code and not a regular word. For this, you''ll surround text in backticks (`, not a single quote). So this code:</p>
<pre><code>You should <span class="hljs-keyword">use</span> the <span class="hljs-string">`strong`</span> tag.
</code></pre><p>becomes: You should use the <code>strong</code> tag.</p>
<p>...which makes much more sense than "You should use the strong tag."</p>
<h2 id="the-title-sequence">The Title Sequence</h2>
<p>Headings are even simpler! For <code>h1</code> through <code>h6</code> tags, all you''ll need is a <code>#</code> before your text. The number of <code>#</code>s you include tells Markdown which header tag you''re using. For example:</p>
<p><code>## This is an h2.</code> </p>
<p><code>### This is an h3.</code></p>
<p>becomes...</p>
<h2 id="this-is-an-h2-">This is an h2.</h2>
<h3 id="this-is-an-h3-">This is an h3.</h3>
<p>Let''s practice!</p>');

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10111, 101, 3, 10111, 'Readable READMEs with Markdown', 11);

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank, description) VALUES(10112, 101, 2, null, 'More Markdown Syntax', 12, '<h1 id="more-markdown">More Markdown</h1>
<p>Now it''s your turn. Dive into the <a target="_blank" href="https://help.github.com/articles/markdown-basics/">Markdown documentation</a>, and explore more ways to write beautiful READMEs.</p>
<p>A few important items you may want to write and should pay extra attention to include:  </p>
<ul>
<li>Ordered and unordered lists</li>
<li>Links and images</li>
<li>Large blocks of code</li>
</ul>
<h2 id="html-is-still-a-thing">HTML Is Still a Thing</h2>
<p>Something to keep in mind when using Markdown is that HTML is still valid in Markdown. If there''s ever something fancy you can''t accomplish with just Markdown, it''s okay to fall back to HTML.</p>
<p>The catch here is that you may be overcomplicating your life. If you need to fall back to plain HTML, there''s a good chance that you could communicate whatever it is you are trying to say in a simpler format.</p>
<h2 id="working-with-md-files">Working with <code>.md</code> Files</h2>
<p>Much like how your HTML files should be saved with a <code>.html</code> extension, your Markdown files should be saved with a <code>.md</code> extension.</p>
<p>Markdown itself can''t be opened in the browser like an HTML document. If you want to preview your Markdown files, <a target="_blank" href="http://dillinger.io/">Dillinger</a> is a great online resource for you to do so.</p>
<p>If you are using Sublime Text, there is a <a target="_blank" href="https://packagecontrol.io/packages/GitHub%20Flavored%20Markdown%20Preview">plugin</a> you can download to let you preview Markdown files right on your computer. If you are using Atom text editor, Markdown preview is baked right into the program (in the ''Packages'' menu).</p>');

INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10113, 101, 1, 10113, 'Markdown Syntax Practice', 13);
INSERT INTO T_SEGMENT(id, lesson_fk, type_fk, video_fk, title, rank) VALUES(10114, 101, 1, 10114, 'Markdown Syntax Practice', 14);
