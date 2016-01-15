# MaskFormatter

An Android Library to format Strings usings masks. Can be used with TextWatcher.

Min SDK version is 7.

### Gradle

```
compile 'com.github.rtoshiro.mflibrary:mflibrary:1.0.0'
```

```
    repositories {
        mavenCentral()
    }
```

### Basic Usage

To format and limit an EditText input, like dates, phone numbers... you can use MaskTextWatcher and SimpleMaskFormatter.

**SimpleMaskFormatter** comes with 5 pre defined patterns you can use.

1. N - for numbers.
2. L - for letters.
3. L - for numbers and letters.
4. l - for lowercase letters.
5. U - for uppedcase letters.

MaskTextWatcher is used to control the input of EditText.
So we have to add the watcher in EditText using **EditText.addTextChangedListener** method.

MaskTextWatcher needs two parameters. The first one is a TextView (or EditText as EditText extends TextView) and the second one is a MaskFormatter object.

Example to format dates like MM/DD/YYYY:

```
SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
MaskTextWatcher mtw = new MaskTextWatcher(myEditText, msf)
myEditText.addTextChangedListener(mtw);
```

In our SimpleMaskFormatter, we limit the usage to numbers only. 

### Custom Patterns

We can define our own pattern using MaskFormatter.

In our example, we are changing it to limit the numbers of our mask string. 

For months, we are going to limit the first character from 0 to 1 and the second character from 0 to 9.

For day, we are going to limit the first character from 0 to 3 and the second character from 0 to 9.

We need to create 3 MaskPattern objects:

```
MaskPattern mp03 = new MaskPattern("[0-3]");
MaskPattern mp09 = new MaskPattern("[0-9]");
MaskPattern mp01 = new MaskPattern("[0-1]");
```

Then, we need to create a new MaskFormmater:

```
MaskFormatter mf = new MaskFormatter("[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]");
```

And register our patterns in MaskFormatter object:

```
mf.registerPattern(mp01);
mf.registerPattern(mp03);
mf.registerPattern(mp09);
```
We can change the pattern representation using our own elements:

 ```
MaskFormatter mf = new MaskFormatter("19/39/9999");

mf.registerPattern("1", mp01);
mf.registerPattern("3", mp03);
mf.registerPattern("9", mp09);
```