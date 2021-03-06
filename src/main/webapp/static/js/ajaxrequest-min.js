/**
 * Copyright 2006-2009 xujiwei
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations
 * under the License.
 */

// //////////////////////////////////////////////////////////////////////////////
// AJAXRequest
// Version: 0.8.10 Patch 4
// Author: xujiwei
// E-mail: vipxjw at 163 dot com
// Website: http://www.xujiwei.com/
//
// AJAXRequest Project Homepage:
// http://ajax.xujiwei.com/
// //////////////////////////////////////////////////////////////////////////////
function AJAXRequest(G) {
	var K = [], $ = this, L = AJAXRequest.__pool__
			|| (AJAXRequest.__pool__ = []);
	(function(E) {
		var D = function() {
		};
		E = E ? E : {};
		var C = ["url", "content", "method", "async", "encode", "timeout",
				"ontimeout", "onrequeststart", "onrequestend", "oncomplete",
				"onexception"], A = ["", "", "GET", true, I("UTF-8"), 3600000,
				D, D, D, D, D], B = C.length;
		while (B--)
			$[C[B]] = _(E[C[B]], A[B]);
		if (!N())
			return false
	})(G);
	function _(_, $) {
		return _ != undefined ? _ : $
	}
	function N() {
		var A, $ = [window.XMLHttpRequest, "MSXML2.XMLHTTP",
				"Microsoft.XMLHTTP"];
		for (var B = 0; B < L.length; B += 1)
			if (L[B].readyState == 0 || L[B].readyState == 4)
				return L[B];
		for (B = 0; B < $.length; B += 1) {
			try {
				A = ($[B] && typeof($[B]) == "function"
						? new $[B]
						: new ActiveXObject($[B]));
				break
			} catch (_) {
				A = false;
				continue
			}
		}
		if (!A) {
			throw "Cannot init XMLHttpRequest object!";
			return false
		} else {
			L[L.length] = A;
			return A
		}
	}
	function E($) {
		return document.getElementById($)
	}
	function C($) {
		var _ = $ * 1;
		return (isNaN(_) ? 0 : _)
	}
	function D($) {
		return (typeof($) == "string" ? ($ = E($)) ? $ : false : $)
	}
	function F() {
		return ((new Date) * 1)
	}
	function M($, _) {
		K[$ + ""] = _
	}
	function H($) {
		return (K[$ + ""])
	}
	function J(_, $, B) {
		return (function A(C) {
			C = _(C);
			for (var E = 0, D = $.length; E < D; E += 1)
				C = C.replace($[E], B[E]);
			return (C)
		})
	}
	function I($) {
		if ($.toUpperCase() == "UTF-8")
			return (encodeURIComponent);
		else
			return (J(escape, [/\+/g], ["%2B"]))
	}
	function O(A, B) {
		if (!A.nodeName)
			return;
		var _ = "|" + A.nodeName.toUpperCase() + "|";
		if ("|INPUT|TEXTAREA|OPTION|".indexOf(_) > -1)
			A.value = B;
		else {
			try {
				A.innerHTML = B
			} catch ($) {
			}
		}
	}
	function P(_) {
		if (typeof(_) == "function")
			return _;
		else {
			_ = D(_);
			if (_)
				return (function($) {
					O(_, $.responseText)
				});
			else
				return $.oncomplete
		}
	}
	function B(_, A, $) {
		var C = 0, B = [];
		while (C < _.length) {
			B[C] = _[C] ? ($[C] ? $[C](_[C]) : _[C]) : A[C];
			C += 1
		}
		while (C < A.length) {
			B[C] = A[C];
			C += 1
		}
		return B
	}
	function A() {
		var E, C = false, K = N(), J = B(arguments, [$.url, $.content,
						$.oncomplete, $.method, $.async, null], [null, null, P,
						null, null, null]), G = J[0], I = J[1], L = J[2], M = J[3], H = J[4], A = J[5], O = M
				.toUpperCase() == "POST" ? true : false;
		if (!G) {
			throw "url is null";
			return false
		}
		var _ = {
			url : G,
			content : I,
			method : M,
			params : A
		};
		if (!O)
			G += (G.indexOf("?") > -1 ? "&" : "?") + "timestamp=" + F();
		K.open(M, G, H);
		$.onrequeststart(_);
		if (O)
			K.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
		K.setRequestHeader("X-Request-With", "XMLHttpRequest");
		E = setTimeout(function() {
					C = true;
					K.abort()
				}, $.timeout);
		var D = function() {
			if (C) {
				$.ontimeout(_);
				$.onrequestend(_)
			} else if (K.readyState == 4) {
				clearTimeout(E);
				_.status = K.status;
				try {
					if (K.status == 200)
						L(K, A);
					else
						$.onexception(_)
				} catch (B) {
					$.onexception(_)
				}
				$.onrequestend(_)
			}
		};
		K.onreadystatechange = D;
		if (O)
			K.send(I);
		else
			K.send("");
		if (H == false)
			D();
		return true
	}
	this.setcharset = function(_) {
		$.encode = I(_)
	};
	this.get = function(C, B, _) {
		return A(C, "", B, "GET", $.async, _)
	};
	this.update = function(H, J, _, D, E) {
		_ = C(_);
		D = C(D);
		if (_ < 1)
			D = 1;
		else if (D < 1)
			D = Number.POSITIVE_INFINITY;
		var B = function() {
			A(J, "", H, "GET", $.async, E)
		}, G = F(), I = function($) {
			B();
			$--;
			if ($ > 0)
				M(G,	setTimeout(function() {
									I($)
								}, _))
		};
		I(D);
		return G
	};
	this.stopupdate = function($) {
		clearTimeout(H($))
	};
	this.post = function(D, _, C, B) {
		return A(D, _, C, "POST", $.async, B)
	};
	this.postf = function(O, J, B) {
		var H = [], L, _, G, I, M, K = arguments.length, C = arguments;
		O = O ? D(O) : false;
		if (!O || O.nodeName != "FORM")
			return false;
		validfoo = O.getAttribute("onvalidate");
		validfoo = validfoo ? (typeof(validfoo) == "string"
				? new Function(validfoo)
				: validfoo) : null;
		if (validfoo && !validfoo())
			return false;
		var E = O.getAttribute("action"), N = O.getAttribute("method"), F = $
				.formToStr(O);
		if (F.length == 0)
			return false;
		if (N.toUpperCase() == "POST")
			return A(E, F, J, "POST", true, B);
		else {
			E += (E.indexOf("?") > -1 ? "&" : "?") + F;
			return A(E, "", J, "GET", true, B)
		}
	};
	this.formToStr = function(C) {
		var B = "", E = "", _, A;
		for (var D = 0; D < C.length; D += 1) {
			_ = C[D];
			if (_.name != "") {
				A = undefined;
				switch (_.type) {
					case "select-one" :
						if (_.selectedIndex > -1)
							A = _.options[_.selectedIndex].value;
						else
							A = "";
						break;
					case "checkbox" :
					case "radio" :
						if (_.checked == true)
							A = _.value;
						break;
					default :
						A = _.value
				}
				if (A != undefined) {
					A = $.encode(A);
					B += E + _.name + "=" + A;
					E = "&"
				}
			}
		}
		return B
	}
}