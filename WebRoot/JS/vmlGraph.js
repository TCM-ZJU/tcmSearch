// 以下是可以修改并且会直接影响外观的属性：
		var rangeH = 580; 	// 背景框的大小
		var rangeW = 640; 	
		
		var bWidth = 90;		// 默认情况下结点框的大小
		var bHeight = 60;
		
		
		var rHeight = 15;		// 关系文本框的高度
		
		var l =220;				// 中心结点与周边结点间的默认中心距离
		
		var step = 10;			// 折叠/展开动画效果的速度
		var totalStep = 60;
		
		// 以下为运行时使用的变量,不要修改
		var dx = 0;					// 绘图板的中心坐标
		var dy = 0;
		
		var bx = new Array();	// 节点运行时位置,以绘图板的中心为坐标系原点
		var by = new Array();
		
		var node = new Array();	// 存放结点对象的数组,最后一个为中心的结点
		var line = new Array();	// 存放关系线对象的数组
		var remark = new Array();	// 存放关系描述的数组
		var boxNum = 0;			// 语义图的维数(即除中心结点外的周边结点的数量)
		var rate = 0;				// 折叠/展开动画中的进度计数器
		
		var draging = false;	// 记录是否正在拖动结点
		var pNode = null;			// 记录背景框的对象
		var selIndex = 0;			// 正在拖动的结点编号
		var hNode = null; 		// 绘图框上面的HMTL元素
		var oriNodePos = new Array();		
		var mousePos = new Array();
		var rw = new Array();
		
		var rsw = 0;
		var rsh = 0;
		
		
		// 设置某结点是否正在被拖动
		function setDrag(d, index) {
			selIndex = index;
			if (d == true) {
				setTop(index);
				if (rate != 0) draging = true;
				
				mousePos[0] = event.x;
				mousePos[1] = event.y;
				
				oriNodePos[0] = bx[index] - event.x * (rsw/rangeW);
				oriNodePos[1] = by[index] - event.y * (rsh/rangeH);
			}
			else {
				draging = false;
				if (event.x != mousePos[0] || event.y != mousePos[1]) {
				} else {
					foldOrExpand();
				}
			}
		}
		
		// 放大某个结点(鼠标悬浮时)
		var isZoomOut = false;
		function mouseEnter(index)
		{
			if (isZoomOut == false) {
				var sl = node[index].length;
				var wc = Math.ceil(Math.sqrt(sl/8/5) * 8);
				var w = wc * 16 + 10;
				var h = Math.ceil(sl/wc) * 17 + 10;
				if (w < bWidth) w = bWidth;
				if (h < bHeight) h = bHeight;
				node[index].style.width = w;
				node[index].style.height = h;
				isZoomOut = true;
			}
		}
		
		// 还原某个结点的大小(鼠标离开)
		function mouseOut(index)
		{
			if (isZoomOut == true) {
				node[index].style.width = bWidth;
				node[index].style.height = bHeight;
				isZoomOut = false;
			}
		}
		// 绘制一个节点
		// x,y 为绘制的坐标
		// index为该节点的编号
		// color为该节点的背景颜色
		function drawNode(x, y, textD, index, color)
		{
			var roundRectStr=
			    "<v:RoundRect onmousedown='setDrag(true, " + index + ")' " + 
			    "onmouseover='mouseEnter(" + index + ")' " +
			    "onmouseout='mouseOut(" + index + ")' " + 
			    "onmouseup='setDrag(false, " + index + ")' " + 
			    "fillcolor = '" + color + "' " +
			    "style='cursor:hand;position:relative;left:" + (x-bWidth/2) + 
			    ";top:" + (y-bHeight/2) + ";width:" + bWidth + ";height:" + bHeight + "'>";
			    
			var roundRect = document.createElement(roundRectStr);
			
			var strElm2 = "<v:TextBox  inset='5px 5px 5px 5px' style='TOP: auto;font-size:9.5pt;color:blue;'  >";
			var elm2 = document.createElement(strElm2);
			var strElm1 = "<v:shadow on='T' type='single' color='#b3b3b3' offset='3px,3px'/>";
			var elm1 = document.createElement(strElm1);
			
			pNode.insertBefore(roundRect);
			
			roundRect.appendChild(elm1);
			roundRect.appendChild(elm2);
			
			//var textNode = document.createTextNode(textD);
			elm2.innerHTML = textD;
			
			return roundRect;
		}
		
		// 绘制一条关系线
		function drawLine(x1,y1,x2,y2)
		{
			var strLine = "<v:line style='position:relative' from='" + x1 + "," + y1 + "' to='"+x2+","+y2+"' />"
			var line = document.createElement(strLine);
			pNode.appendChild(line);

			return line;
		}
		
		// 绘制关系描述文本框
		function drawRemark(x1,y1,x2,y2,remark) {
			var rWidth = remark.length * 12;
			var boxStr =
			    "<v:Rect strokecolor='white' style='position:relative;left:" + ((x1+x2-rWidth)/2) + 
			    ";top:" + ((y1+y2-rHeight)/2) + ";width:" + rWidth + ";height:" + rHeight + "'>";
			var boxNode = document.createElement(boxStr);
			var textStr = "<v:TextBox  inset='1px 1px 1px 1px' style='font-size:9.5pt;color:blue;'  >";
			var textNode = document.createElement(textStr);
			boxNode.appendChild(textNode);
			
			//var remarkNode = document.createTextNode(remark);
			boxNode.innerHTML = remark;
			
			pNode.appendChild(boxNode);
			
			return boxNode;
		}
		
		// 创建语义图的内容
		// tSize为语义图的维数
		// textA为长度为tSize+1的字符串数组,为结点的描述,其中最后一个为中心结点的描述
		// remarkText为长度为tSize的关系描述的字符串数组
		function createContent(tSize, textA, remarkText)
		{	
			if (pNode != null && boxNum == 0)
			{	
				step = Math.abs(step);
				rate = 0;
				boxNum = tSize;	
				
				for (var i=0; i<tSize; i++)
				{
					bx[i] = l*Math.cos(Math.PI*2/(tSize)*i);
					by[i] = l*Math.sin(Math.PI*2/(tSize)*i);
					
					line[i] = drawLine(dx, dy , dx, dy);
					remark[i] = drawRemark(dx, dy, dx, dy, remarkText[i]);
					rw[i] = remarkText[i].length * 16;
					node[i] = drawNode(dx, dy , textA[i], i, "#B8E5FF");
				}
				bx[tSize] = 0;
				by[tSize] = 0;
				node[tSize] = drawNode(dx, dy, textA[tSize], tSize, "yellow");
	
				foldOrExpand();
			}
		}
		
		// 清除绘图板的内容
		function clearContent()
		{
			if (boxNum > 0)
			{
				for (var i=0; i<boxNum; i++)
				{
					pNode.removeChild(line[i]);
					pNode.removeChild(node[i]);
					pNode.removeChild(remark[i]);
				}
				pNode.removeChild(node[boxNum]);
				boxNum = 0;	
			}
			
		}
		
		// 展开或折叠语义图
		function foldOrExpand()
		{
			if (rate < totalStep && step > 0 || rate > 0 && step < 0) {
				rate = rate + step;
				for (var j=0; j<boxNum; j++)
				{
					node[j].style.left=dx + bx[boxNum] + (bx[j]-bx[boxNum])*rate/totalStep - bWidth/2;
					node[j].style.top=dy + by[boxNum] + (by[j]-by[boxNum])*rate/totalStep - bHeight/2;
					
					line[j].to.x = dx + (bx[j] - bx[boxNum])*3/4*rate/totalStep + bx[boxNum];
					line[j].to.y = dy + (by[j] - by[boxNum])*3/4*rate/totalStep + by[boxNum];
					
					line[j].from.x = dx + (bx[j] - bx[boxNum])/4*rate/totalStep + bx[boxNum];
					line[j].from.y = dy + (by[j] - by[boxNum])/4*rate/totalStep + by[boxNum];
					
					remark[j].style.left=dx + bx[boxNum] + (bx[j]-bx[boxNum])/2*rate/totalStep -rw[j]/2;
					remark[j].style.top=dy + by[boxNum] + (by[j]-by[boxNum])/2*rate/totalStep - rHeight/2;
				}
				if (rate != totalStep && rate != 0) setTimeout("foldOrExpand()",3);
				else step = -step;
			}
		}
		
		// 拖动结点,具体的节点,状态等信息在全局变量中记录
		function dragNode()
		{
			if (draging == true) {
				bx[selIndex] = oriNodePos[0] + event.x * (rsw/rangeW);
				by[selIndex] = oriNodePos[1] + event.y * (rsh/rangeH);
				
				if (bx[selIndex] + dx - bWidth/2 < 0) bx[selIndex] = bWidth/2 - dx;
				if (bx[selIndex] + dx + bWidth/2 > rangeW) bx[selIndex] = rangeW - bWidth/2 - dx;
				
				if (by[selIndex] + dx - bHeight/2 < 0) by[selIndex] = bHeight/2 - dy;
				if (by[selIndex] + dx + bHeight/2 > rangeH) by[selIndex] = rangeH - bHeight/2 - dy;
				
				node[selIndex].style.left  = bx[selIndex] + dx - bWidth/2;
				node[selIndex].style.top = by[selIndex] + dy - bHeight/2;
				
				if (selIndex < boxNum) {
					line[selIndex].to.x = dx + (bx[selIndex] - bx[boxNum])*3/4 + bx[boxNum];
					line[selIndex].to.y = dy + (by[selIndex] - by[boxNum])*3/4 + by[boxNum];
					
					line[selIndex].from.x = dx + (bx[selIndex] - bx[boxNum])/4 + bx[boxNum];
					line[selIndex].from.y = dy + (by[selIndex] - by[boxNum])/4 + by[boxNum];
					
					remark[selIndex].style.left=dx + (bx[selIndex]+bx[boxNum]-rw[selIndex])/2*rate/totalStep;
					remark[selIndex].style.top=dy + (by[selIndex]+by[boxNum]-rHeight)/2*rate/totalStep;
				} else {
					for (var i=0; i<boxNum; i++) {
						line[i].to.x = dx + (bx[i] - bx[boxNum])*3/4 + bx[boxNum];
						line[i].to.y = dy + (by[i] - by[boxNum])*3/4 + by[boxNum];
					
						line[i].from.x = dx + (bx[i] - bx[boxNum])/4 + bx[boxNum];
						line[i].from.y = dy + (by[i] - by[boxNum])/4 + by[boxNum];
						
						remark[i].style.left=dx + (bx[i]+bx[boxNum]-rw[i])/2*rate/totalStep;
						remark[i].style.top=dy + (by[i]+by[boxNum]-rHeight)/2*rate/totalStep;
					}
				}
			}
		}
		
		// 把某结点移到顶层
		function setTop(index)
		{
			if (index < boxNum) {
				pNode.appendChild(line[index]);
				pNode.appendChild(remark[index]);
			}
			pNode.appendChild(node[index]);
		}
		
		// 放大或缩小结点
		// rate比例,大于1为放大,小于1为缩小
		function resize(rate)
		{
			bHeight = bHeight * rate;
			bWidth = bWidth * rate;

			//pNode.style.width = rsw/rate;
			//pNode.style.height = rsh/rate;
			
			//rsw /= rate;
			//rsh /= rate;
			
			//pNode.coordsize = rsw + "," + rsh;
			//pNode.style.coordsize.height = rsh;
			
			
			if (boxNum > 0)
			{
				for (var i=0; i<boxNum; i++)
				{			
					node[i].style.left = dx + bx[i] - bWidth/2;
					node[i].style.width = bWidth;
					
					node[i].style.top = dy + by[i] - bHeight/2;
					node[i].style.height = bHeight;
				}
				node[boxNum].style.left = dx + bx[boxNum] - bWidth/2;
				node[boxNum].style.width = bWidth;
				
				node[boxNum].style.top = dy + by[boxNum] - bHeight/2;
				node[boxNum].style.height = bHeight;
			}
		}
		
		// 创建放大和缩小按钮
		function createButton(left, top, width, height, str, rate)
		{
			var rectStr=
			    "<v:roundrect onmousedown='resize(" + rate + ")' " + 
			    "style='position:relative;left:" + left + 
			    ";top:" + top + ";width:" + width + ";height:" + height + ";PADDING-TOP:6;cursor:hand;font-size:13px;TEXT-ALIGN:center;margin-bottom:0;'>";
			    
			var rect = document.createElement(rectStr);
			
			//var textStr = "<v:TextBox  inset='2px 2px 2px 2px' style='TOP: auto;font-size:10.2pt;color:blue;'  >";
			var textStr = "<v:fill type=gradient opacity=0.4 color=#B8E5FF color2=#B8E5FF  angle='50'/>";
			var textElm = document.createElement(textStr);		
			
			rect.appendChild(textElm);
			
			//var textNode = document.createTextNode(str);
			textElm.innerHTML = str;
			
			return rect;
		}
		
		// 创建绘图板
		// hNode为绘图板目标位置的前一个HTML元素
		function createBackGround(headerNode) 
		{
			if (pNode == null)
			{
				hNode = headerNode;
				
				rsw = rangeW;
				rsh = rangeH;
				
				dx = rangeW / 2;
				dy = rangeH / 2;
				
				var bgStr = "<v:group ID='bgNode' fillcolor ='#e5ecf9'  style='WIDTH:" + rangeW + ";HEIGHT:" + rangeH + ";' coordsize = '" + rangeW + "," + rangeH + "'  onMouseMove='dragNode()'>"
				var bg = document.createElement(bgStr);
				hNode.parentNode.insertBefore(bg, hNode);
				hNode.parentNode.insertBefore(hNode, bg);
				
				var bgbStr = "<v:Rect ID='bgbox' strokecolor='#237DB1' style='position:relative;left:0;top:0;width:" + rangeW + ";height:" + rangeH + "'>";
				var bgb = document.createElement(bgbStr);
				bg.appendChild(bgb);
				
				bg.appendChild(createButton(rangeW - 130, 5, 55, 20, "放大", 1.2));
				bg.appendChild(createButton(rangeW - 70, 5, 63, 20, "缩小", 0.8));
				
				pNode = bg;
			}
		}
		
		// 删除绘图板
		function clearBackGround()
		{
			if (pNode != null)
			{
				if (boxNum > 0) clearContent();
				pNode.parentNode.removeChild(pNode);
				pNode = null;
				hNode = null;
			}
		}		
		