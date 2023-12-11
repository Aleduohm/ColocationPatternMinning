# ColocationPatternMinning
基于pytorch的GlobalPointer进行三元组抽取。

具体使用说明：

1、在data/ske/raw_data下是原始数据，新建一个process.py，主要是得到mid_data下的关系的类型。
2、针对于不同的数据源，在data_loader.py中修改MyDataset类下，返回的是一个列表，列表中的每个元素是：(text, labels)，其中labels是[[主体，类别，客体]]。
3、运行main.py进行训练、验证、测试和预测。
数据和模型下载地址：链接：https://pan.baidu.com/s/1HOaGUiRsknIBtXS_ASNF-w?pwd=rm2u 提取码：rm2u

依赖
pytorch==1.6.0
transformers==4.5.0
tensorboardX
特别注意：eval_steps要根据总的steps进行合理设置，不要设置得太小，否则在初期就进行验证会极慢（因为预测的负样本太多了）。

运行
python main.py \
--bert_dir="model_hub/chinese-bert-wwm-ext/" \
--data_dir="./data/ske/" \
--log_dir="./logs/" \
--output_dir="./checkpoints/" \
--num_tags=49 \
--seed=123 \
--gpu_ids="0" \
--max_seq_len=256 \
--lr=5e-5 \
--other_lr=5e-5 \
--train_batch_size=32 \
--train_epochs=1 \
--eval_steps=500 \
--eval_batch_size=8 \
--max_grad_norm=1 \
--warmup_proportion=0.1 \
--adam_epsilon=1e-8 \
--weight_decay=0.01 \
--dropout_prob=0.1 \
--use_tensorboard="False" \
--use_dev_num=1000
结果
这里以batch_size=32运行了2000步。

precision:0.7831715210355987 recall:0.7298578199052133 f1:0.7555753791257805
文本： 查尔斯·阿兰基斯（Charles Aránguiz），1989年4月17日出生于智利圣地亚哥，智利职业足球运动员，司职中场，效力于德国足球甲级联赛勒沃库森足球俱乐部
主体： [['查尔斯·阿兰基斯']]
客体： [['智利', '圣地亚哥', '智利圣地亚哥', '1989年4月17日']]
关系： [[('查尔斯·阿兰基斯', '出生日期', '1989年4月17日'), ('查尔斯·阿兰基斯', '出生地', '智利'), ('查尔斯·阿兰基斯', '国籍', '智利'), ('查尔斯·阿兰基斯', '出生地', '智利圣地亚哥'), ('查尔斯·阿兰基斯', '国籍', '智利圣地亚哥'), ('查尔斯·阿兰基斯', '出生地', '圣地亚哥')]]
====================================================================================================
文本： 《离开》是由张宇谱曲，演唱
主体： [['离开']]
客体： [['张宇']]
关系： [[('离开', '歌手', '张宇'), ('离开', '作曲', '张宇')]]
====================================================================================================
文本： 《愤怒的唐僧》由北京吴意波影视文化工作室与优酷电视剧频道联合制作，故事以喜剧元素为主，讲述唐僧与佛祖打牌，得罪了佛祖，被踢下人间再渡九九八十一难的故事
主体： [['愤怒的唐僧']]
客体： [['北京吴意波影视文化工作室']]
关系： [[('愤怒的唐僧', '出品公司', '北京吴意波影视文化工作室')]]
====================================================================================================
文本： 李治即位后，萧淑妃受宠，王皇后为了排挤萧淑妃，答应李治让身在感业寺的武则天续起头发，重新纳入后宫
主体： [['李治', '萧淑妃']]
客体： [['李治', '萧淑妃']]
关系： [[('李治', '妻子', '萧淑妃'), ('萧淑妃', '丈夫', '李治')]]
====================================================================================================
文本： 《工业4.0》是2015年机械工业出版社出版的图书，作者是（德）阿尔冯斯·波特霍夫，恩斯特·安德雷亚斯·哈特曼
主体： [['工业4.0']]
客体： [['机械工业出版社', '阿尔冯斯·波特霍夫']]
关系： [[('工业4.0', '出版社', '机械工业出版社'), ('工业4.0', '作者', '阿尔冯斯·波特霍夫')]]
====================================================================================================
文本： 周佛海被捕入狱之后，其妻杨淑慧散尽家产请蒋介石枪下留人，于是周佛海从死刑变为无期，不过此人或许作恶多端，改判没多久便病逝于监狱，据悉是心脏病发作
主体： [['周佛海', '杨淑慧', '蒋介石']]
客体： [['周佛海', '杨淑慧', '蒋介石']]
关系： [[('周佛海', '妻子', '杨淑慧'), ('杨淑慧', '丈夫', '周佛海'), ('杨淑慧', '丈夫', '蒋介石'), ('蒋介石', '妻子', '杨淑慧')]]
====================================================================================================
文本： 《李烈钧自述》是2011年11月1日人民日报出版社出版的图书，作者是李烈钧
主体： [['李烈钧自述']]
客体： [['李烈钧', '人民日报出版社']]
关系： [[('李烈钧自述', '作者', '李烈钧'), ('李烈钧自述', '出版社', '人民日报出版社')]]
====================================================================================================
文本： 除演艺事业外，李冰冰热心公益，发起并亲自参与多项环保慈善活动，积极投身其中，身体力行担起了回馈社会的责任于02年出演《少年包青天》，进入大家视线
主体： [['少年包青天']]
客体： [['李冰冰']]
关系： [[('少年包青天', '主演', '李冰冰')]]
====================================================================================================
文本： 马志舟，1907年出生，陕西三原人，汉族，中国共产党，任红四团第一连连长，1933年逝世
主体： [['马志舟']]
客体： [['汉族', '1907年', '中国', '陕西三原']]
关系： [[('马志舟', '出生日期', '1907年'), ('马志舟', '出生地', '陕西三原'), ('马志舟', '民族', '汉族'), ('马志舟', '国籍', '中国')]]
====================================================================================================
文本： 斑刺莺是雀形目、剌嘴莺科的一种动物，分布于澳大利亚和新西兰，包括澳大利亚、新西兰、塔斯马尼亚及其附近的岛屿
主体： [['斑刺莺']]
客体： [['雀形目']]
关系： [[('斑刺莺', '目', '雀形目')]]
====================================================================================================
文本： 《课本上学不到的生物学2》是2013年上海科技教育出版社出版的图书
主体： [['课本上学不到的生物学2']]
客体： [['上海科技教育出版社']]
关系： [[('课本上学不到的生物学2', '出版社', '上海科技教育出版社')]]
====================================================================================================
参考
模型参考：bert4torch/task_relation_extraction_gplinker.py

将“softmax+交叉熵”推广到多标签分类问题 - 科学空间|Scientific Spaces

GPLinker：基于GlobalPointer的实体关系联合抽取 - 科学空间|Scientific Spaces
